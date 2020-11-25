using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PricingService.Data;
using PricingService.Models;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace PricingService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PricingController : ControllerBase
    {
        private readonly PricingContext _context;

        public PricingController(PricingContext context)
        {
            _context = context;
        }

        /// <summary>
        /// For testing purpose, seeds the database with dummy data.
        /// </summary>
        /// <returns>List of accommodations.</returns>
        [HttpGet("feed")]
        public async Task<ActionResult<List<AccommodationDTO>>> Feed()
        {
            var accommodations = AccommodationSeeder.GetGeneratedAccommodations();
            

            _context.Accommodations.AddRange(accommodations);
            await _context.SaveChangesAsync();

            var accommodationDTOs = accommodations.Select(x => Converter.ConvertModelToAccommodationDTO(x)).ToList();
            
            return accommodationDTOs;
        }

        /// <summary>
        /// Gets a collection of Accommodations.
        /// </summary>
        /// <param name="pageSize">Number of entries.</param>
        /// <param name="page">Pagenumber</param>
        /// <returns>List of accommodations.</returns>
        [HttpGet("{page:int?}")]
        public async Task<ActionResult<List<AccommodationDTO>>> Get(int? pageSize, int page = 0)
        {
            var accommodations = await _context.Accommodations
                .Include(a => a.PriceIntervals)
                .Skip( page * (pageSize ?? 0))
                .Take(pageSize ?? int.MaxValue)
                .Select(x => Converter.ConvertModelToAccommodationDTO(x))
                .ToListAsync();

            return accommodations;
        }

        /// <summary>
        /// Get a specific accomodation's data.
        /// </summary>
        /// <param name="id">Id of the requested accommodation.</param>
        /// <returns>Accommodation object.</returns>
        [HttpGet("accommodation/{id}")]
        public async Task<ActionResult<AccommodationDTO>> Get(string id)
        {
            var result = await _context.Accommodations
                .Include(a => a.PriceIntervals)
                .Where(x => x.AccommodationId == id)
                .FirstOrDefaultAsync();

            if (result != null)
            {
                return new AccommodationDTO().ConvertFromModel(result);
            }

            return BadRequest();

        }

        /// <summary>
        /// Post new accommodation
        /// </summary>
        /// <param name="accommodation">Data by accommodation objectm</param>
        /// <returns>URL to check new object, and the new object.</returns>
        [HttpPost("accommodation")]
        public ActionResult Post(AccommodationDTO accommodation)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest();
            }
            var model = accommodation.ConvertToModel();


            _context.Accommodations.Add(model);
            _context.SaveChanges();


            return CreatedAtAction(
                nameof(Get), // TODO: this produces /feed?id="" url.
                new { id = model.AccommodationId},
                accommodation.ConvertFromModel(model)
                );
        }

        /// <summary>
        /// Post new date interval for existing accommodation.
        /// </summary>
        /// <param name="accommodationId">Id of the accommodation.</param>
        /// <param name="interval">Data as interval object.</param>
        /// <returns>The created interval object.</returns>
        [HttpPost("{accommodationId}/interval")]
        public ActionResult Post([FromRoute]string accommodationId, [FromBody] PriceIntervalDTO interval)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest("Server was not able to read the data due to bad format.");
            }

            var requestedAcco = _context.Accommodations
                .Include(a => a.PriceIntervals)
                .Where(a => a.AccommodationId == accommodationId)
                .FirstOrDefault();

            if (requestedAcco == null)
            {
                return BadRequest("This accomodation was not found.");
            }

            var intervalModel = interval.ConvertToModel();
            requestedAcco.PriceIntervals.Add(intervalModel);
            _context.SaveChanges();

            return Ok(interval.ConvertFromModel(intervalModel));
        }

        /// <summary>
        /// Modify the base price of an accommodation
        /// </summary>
        /// <param name="accommodationId">Id of the accommodation.</param>
        /// <param name="basePrice">Value of the price.</param>
        [HttpPut("Accommodation/{accommodationId}")]
        public async Task<ActionResult> Put(string accommodationId, [FromQuery]decimal basePrice)
        {
            var targetAccommodation = _context.Accommodations
                .Where(a => a.AccommodationId == accommodationId)
                .FirstOrDefault();

            if (targetAccommodation != null)
            {
                targetAccommodation.BasePrice = basePrice;
                await _context.SaveChangesAsync();

                return Ok();
            }

            return BadRequest();
        }

        /// <summary>
        /// Modify an interval's data.
        /// </summary>
        /// <param name="accommodationId">Id the of the accommodation that the interval belongs to.</param>
        /// <param name="intervalId">Id of the interval.</param>
        /// <param name="interval">Interval object data.</param>
        /// <returns>HTTP Response.</returns>
        [HttpPut("{accommodationId}/interval/{intervalId}")]
        public async Task<ActionResult> Put(string accommodationId, string intervalId, [FromBody] PriceIntervalDTO interval)
        {
            var targetInterval = await _context.Accommodations
                .Include(a => a.PriceIntervals)
                .Where(a => a.AccommodationId == accommodationId)
                .Select(a => a.PriceIntervals.Where(p => p.PriceIntervalId == intervalId).FirstOrDefault())
                .FirstOrDefaultAsync();

            if (targetInterval != null)
            {
                interval.OverwriteRefModel(ref targetInterval);
                await _context.SaveChangesAsync();
                
                return Ok();
            }

            return BadRequest();
               
        }

        /// <summary>
        /// Delete a specific accommodation.
        /// </summary>
        /// <param name="accommodationId">Id of the accommodation.</param>
        [HttpDelete("{accommodationId}")]
        public async Task<ActionResult> Delete(string accommodationId)
        {
            var accommodation = await _context.Accommodations.FindAsync(accommodationId);

            if (accommodation == null)
            {
                return BadRequest();
            }

            _context.Accommodations.Remove(accommodation);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        /// <summary>
        /// Deletes all intervals of an accommodation
        /// </summary>
        /// <param name="accommodationId">Id of accommodation</param>
        /// <returns>HTTP Result</returns>
        [HttpDelete("all/{accommodationId}")]
        public async Task<ActionResult> DeleteAllIntervals(string accommodationId)
        {
            var accommodation = await _context.Accommodations
                .Include(a => a.PriceIntervals)
                .Where(a => a.AccommodationId == accommodationId)
                .SingleOrDefaultAsync();

            if (accommodation == null)
            {
                return BadRequest();
            }

            accommodation.PriceIntervals = null;
            await _context.SaveChangesAsync();

            return NoContent();
        }

        /// <summary>
        /// Deletes an interval.
        /// </summary>
        /// <param name="intervalId">Id of the interval.</param>
        /// <returns>HTTP result</returns>
        [HttpDelete("interval/{intervalId}")]
        public async Task<ActionResult> DeleteInterval(string intervalId)
        {
            var interval = await _context.PriceIntervals.FindAsync(intervalId);

            if (interval == null)
            {
                return BadRequest();
            }

            _context.PriceIntervals.Remove(interval);
            await _context.SaveChangesAsync();

            return NoContent();
        }

    }
}
