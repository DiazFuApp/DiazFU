using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class ReferenciasPromotoresController : ApiController
    {
        ReferenciasPromotores Referencia = new ReferenciasPromotores();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/referenciaspromotores/ejemplo")]
        public ReferenciasPromotores Example()
        {
            return Referencia;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LAS REFERENCIAS DEL PROMOTOR
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/referenciaspromotores")]
        public List<ReferenciasPromotores> GetAll()
        {
            return Referencia.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR LA REFERENCIA DEL PROMOTOR
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/referenciaspromotores/{id}")]
        public List<ReferenciasPromotores> Get(int id)
        {
            Referencia.Id = id;
            return Referencia.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR LA REFERENCIA DEL PROMOTOR
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/referenciaspromotores/objeto")]
        public List<ReferenciasPromotores> Get([FromBody]ReferenciasPromotores Referencia)
        {
            return Referencia.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UNA NUEVA REFERENCIA DEL PROMOTOR
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/referenciaspromotores/agregar")]
        public ReferenciasPromotores Add([FromBody]ReferenciasPromotores Referencia)
        {
            Referencia.Agregar();
            return Referencia;
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UNA REFERENCIA DEL PROMOTOR
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/referenciaspromotores/actualizar")]
        public ReferenciasPromotores Update([FromBody]ReferenciasPromotores Referencia)
        {
            Referencia.Actualizar();
            return Referencia;
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UNA REFERENCIA DEL PROMOTOR
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/referenciasprestamos/eliminar")]
        public ReferenciasPromotores Delete([FromBody]ReferenciasPromotores Referencia)
        {
            Referencia.IdEstatus = 2;
            Referencia.Actualizar();
            return Referencia;
        }
    }
}
