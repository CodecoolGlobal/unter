using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace PricingService.Models
{
    public class PriceInterval
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public string PriceIntervalId { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public decimal Price { get; set; }

        public string AccommodationId { get; set; }
        public Accommodation Accommodation { get; set; }
    }
}
