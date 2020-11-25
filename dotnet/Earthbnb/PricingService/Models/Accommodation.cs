using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;

namespace PricingService.Models
{
    public class Accommodation
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public string AccommodationId { get; set; }
        public decimal BasePrice { get; set; }
        public string CurrencyISO { get; set; }
        public ICollection<PriceInterval> PriceIntervals { get; set; }
    }
}
