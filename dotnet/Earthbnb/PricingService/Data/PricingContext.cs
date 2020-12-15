using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using PricingService.Models;

namespace PricingService.Data
{
    public class PricingContext : DbContext
    {
        public PricingContext(DbContextOptions<PricingContext> options)
            : base(options)
        {
        }

        public DbSet<Accommodation> Accommodations { get; set; }
        public DbSet<PriceInterval> PriceIntervals { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Accommodation>()
                .HasKey(a => a.AccommodationId);

            modelBuilder.Entity<Accommodation>()
                .HasMany(a => a.PriceIntervals)
                .WithOne(p => p.Accommodation);

            modelBuilder
                .Entity<Accommodation>()
                .Property(a => a.AccommodationId)
                // prevent user to enter a value for this property
                .Metadata.SetBeforeSaveBehavior(PropertySaveBehavior.Ignore);

        }
    }
}
