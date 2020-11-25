using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using PricingService.Models;

namespace PricingService.Data
{
#nullable enable

    public record AccommodationDTO
    {
        public string? AccommodationId { get; set; }
        [Required]
        public decimal BasePrice { get; set; }
        [Required]
        [StringLength(3)]
        public string CurrencyISO { get; set; }
        public ICollection<PriceIntervalDTO> PriceIntervals { get; set; } = new List<PriceIntervalDTO>();


        public Accommodation ConvertToModel()
        {
            var obj = new Accommodation()
            {
                BasePrice = BasePrice,
                CurrencyISO = CurrencyISO,
            };

            if (PriceIntervals != null)
            {
                obj.PriceIntervals = PriceIntervals.Select(x => x.ConvertToModel()).ToList();
            }

            return obj;
        }

        public AccommodationDTO ConvertFromModel(Accommodation model)
        {
            return new AccommodationDTO()
            {
                AccommodationId = model.AccommodationId,
                BasePrice = model.BasePrice,
                CurrencyISO = model.CurrencyISO,
                PriceIntervals = model.PriceIntervals
                    .Select(pi => new PriceIntervalDTO(pi.StartDate, pi.EndDate, pi.Price) { PriceIntervalId = pi.PriceIntervalId })
                    .ToList()
            };
        }
    }

    public record PriceIntervalDTO(
        DateTime StartDate,
        DateTime EndDate,
        decimal Price)
    {
        public string? PriceIntervalId { get; set; }
        public string? AccommodationId { get; set; }



        public PriceInterval ConvertToModel()
        {
            return new PriceInterval()
            {
                StartDate = this.StartDate,
                EndDate = this.EndDate,
                Price = this.Price
            };
        }

        public PriceIntervalDTO ConvertFromModel(PriceInterval model)
        {
            return new PriceIntervalDTO(
                model.StartDate,
                model.EndDate,
                model.Price)
            {
                PriceIntervalId = model.PriceIntervalId,
                AccommodationId = model.AccommodationId
            };
        }

        public void OverwriteRefModel(ref PriceInterval model)
        {
            model.StartDate = StartDate;
            model.EndDate = EndDate;
            model.Price = Price;
        }

    }

    public static partial class Converter
    {
        public static AccommodationDTO ConvertModelToAccommodationDTO(Accommodation model)
        {
            return new AccommodationDTO()
            {
                AccommodationId = model.AccommodationId,
                BasePrice = model.BasePrice,
                CurrencyISO = model.CurrencyISO,
                PriceIntervals = model.PriceIntervals
                    .Select(pi => new PriceIntervalDTO(pi.StartDate, pi.EndDate, pi.Price) { PriceIntervalId = pi.PriceIntervalId })
                    .ToList()
            };
        }
    }



}
