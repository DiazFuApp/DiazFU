using Microsoft.Owin;
using Owin;

[assembly: OwinStartup(typeof(diazfuappService.Startup))]

namespace diazfuappService
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureMobileApp(app);
        }
    }
}