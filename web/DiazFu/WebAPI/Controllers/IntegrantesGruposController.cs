using System.Collections.Generic;
using System.Web.Http;
using WebAPI.Models;


namespace WebAPI.Controllers
{
    public class IntegrantesGruposController : ApiController
    {
        IntegrantesGrupos IntegranteGrupo = new IntegrantesGrupos();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/integrantesgrupos/ejemplo")]
        public IntegrantesGrupos Example()
        {
            return IntegranteGrupo;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS CLIENTES
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/integrantesgrupos")]
        public List<IntegrantesGrupos> GetAll()
        {
            return IntegranteGrupo.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL CLIENTE
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/integrantesgrupos/{id}")]
        public List<IntegrantesGrupos> Get(int id)
        {
            IntegranteGrupo.IdGrupo = id;
            return IntegranteGrupo.ConsultarIDGrupo();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO CLIENTE
        /// </summary>
        /// <param name="Cliente"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/integrantesgrupos/agregar")]
        public IntegrantesGrupos Add([FromBody]IntegrantesGrupos IntegranteGrupo)
        {
            IntegranteGrupo.Agregar();
            return IntegranteGrupo;
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN CLIENTE
        /// </summary>
        /// <param name="Cliente"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/integrantesgrupos/actualizar")]
        public IntegrantesGrupos Update([FromBody]IntegrantesGrupos IntegranteGrupo)
        {
            IntegranteGrupo.Actualizar();
            return IntegranteGrupo;
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN CLIENTE
        /// </summary>
        /// <param name="Cliente"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/integrantesgrupos/eliminar")]
        public IntegrantesGrupos Delete([FromBody]IntegrantesGrupos IntegranteGrupo)
        {
            IntegranteGrupo.IdEstatus = 2;
            IntegranteGrupo.Actualizar();
            return IntegranteGrupo;
        }
    }
}