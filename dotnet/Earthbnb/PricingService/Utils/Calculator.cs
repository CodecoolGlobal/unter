using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using PricingService.Models;

namespace PricingService.Utils
{
    public class Calculator
    {
        internal decimal GetFee(Accommodation accommodation, int daysReserved)
        {
            return accommodation.BasePrice * daysReserved;
        }

        internal decimal GetFee(Accommodation accommodation, DateTime checkinDate, int daysReserved)
        {
            decimal fee = 0.00M;

            var shortenedIntervals = accommodation.PriceIntervals
                .Where(interv => interv.EndDate > checkinDate)
                .Select(x => x);

            for (int i = 0; i < daysReserved; i++)
            {
                var price = shortenedIntervals
                .Where(interv => interv.StartDate <= checkinDate.AddDays(i) && interv.EndDate >= checkinDate.AddDays(i))
                .Select(interv => interv.Price)
                .DefaultIfEmpty(accommodation.BasePrice).First();

                fee += price;
            }

            return fee;
        }
    }
}
