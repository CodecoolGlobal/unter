using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using PricingService.Models;

namespace PricingService.Data
{
    public class AccommodationSeeder
    {
        public static List<Accommodation> GetGeneratedAccommodations()
        {
            return new List<Accommodation>()
            {
                new Accommodation()
                {
                    BasePrice = 500,
                    CurrencyISO = "USD",
                    PriceIntervals = new List<PriceInterval>()
                    {
                        new PriceInterval
                        {
                            StartDate = new DateTime(2020, 06, 1),
                            EndDate = new DateTime(2020, 09, 1),
                            Price = 1000
                        },
                        new PriceInterval
                        {
                            StartDate = new DateTime(2020, 12, 1),
                            EndDate = new DateTime(2020, 12, 31),
                            Price = 800
                        }
                    }
                },
                new Accommodation()
                {
                    BasePrice = 5000,
                    CurrencyISO = "HUF",
                    PriceIntervals = new List<PriceInterval>()
                    {
                        new PriceInterval
                        {
                            StartDate = new DateTime(2020, 7, 1),
                            EndDate = new DateTime(2020, 8, 31),
                            Price = 6000
                        },
                        new PriceInterval
                        {
                            StartDate = new DateTime(2020, 12, 1),
                            EndDate = new DateTime(2020, 12, 31),
                            Price = 10000
                        },
                        new PriceInterval
                        {
                            StartDate = new DateTime(2020, 04, 1),
                            EndDate = new DateTime(2020, 04, 10),
                            Price = 8000
                        }

                    }
                },
                new Accommodation()
                {
                    BasePrice = 8000,
                    CurrencyISO = "HUF",
                    PriceIntervals = new List<PriceInterval>()
                    {
                        new PriceInterval
                        {
                            StartDate = new DateTime(2020, 1, 1),
                            EndDate = new DateTime(2020, 5, 31),
                            Price = 5000
                        }
                    }
                },
            };
        }
    }
}
