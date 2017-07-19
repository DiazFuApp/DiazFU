namespace diazfuappService.Migrations
{
    using Microsoft.Azure.Mobile.Server.Tables;
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<diazfuappService.Models.diazfuappContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
            AutomaticMigrationDataLossAllowed = true;
            SetSqlGenerator("System.Data.SqlClient", new EntityTableSqlGenerator());
        }

        protected override void Seed(diazfuappService.Models.diazfuappContext context)
        {
            //  This method will be called after migrating to the latest version.

            //You can use the DbSet<T>.AddOrUpdate() helper extension method
            //to avoid creating duplicate seed data.E.g.

              //context.Sample.AddOrUpdate(
              //  p => p.Nombre,
              //  new Pe { FullName = "Andrew Peters" },
              //  new Person { FullName = "Brice Lambson" },
              //  new Person { FullName = "Rowan Miller" }
              //);

        }
    }
}
