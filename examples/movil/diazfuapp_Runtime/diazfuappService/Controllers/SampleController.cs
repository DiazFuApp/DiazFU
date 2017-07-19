using System.Linq;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Controllers;
using System.Web.Http.OData;
using Microsoft.Azure.Mobile.Server;
using diazfuappService.DataObjects;
using diazfuappService.Models;

namespace diazfuappService.Controllers
{
    public class SampleController : TableController<Sample>
    {
        protected override void Initialize(HttpControllerContext controllerContext)
        {
            base.Initialize(controllerContext);
            diazfuappContext context = new diazfuappContext();
            DomainManager = new EntityDomainManager<Sample>(context, Request);
        }

        // GET tables/TodoItem
        public IQueryable<Sample> GetAllSamples()
        {
            return Query();
        }

        // GET tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public SingleResult<Sample> GetSample(string id)
        {
            return Lookup(id);
        }

        // PATCH tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public Task<Sample> PatchSample(string id, Delta<Sample> patch)
        {
            return UpdateAsync(id, patch);
        }

        // POST tables/TodoItem
        public async Task<IHttpActionResult> PostSample(Sample item)
        {
            Sample current = await InsertAsync(item);
            return CreatedAtRoute("Tables", new { id = current.Id }, current);
        }

        // DELETE tables/TodoItem/48D68C86-6EA6-4C25-AA33-223FC9A27959
        public Task DeleteSample(string id)
        {
            return DeleteAsync(id);
        }
    }
}