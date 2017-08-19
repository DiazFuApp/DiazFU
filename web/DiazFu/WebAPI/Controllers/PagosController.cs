using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class PagosController : ApiController
    {
        Pagos Pagos = new Pagos();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/pagos/ejemplo")]
        public Pagos Example()
        {
            return Pagos;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS PAGOS
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/pagos")]
        public List<Pagos> GetAll()
        {
            return Pagos.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL PAGOS POR EL ID
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/pagos/{id}")]
        public List<Pagos> Get(int id)
        {
            Pagos.Id = id;
            return Pagos.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL PAGOS POR EL OBJETO
        /// </summary>
        /// <param name="Pago"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/pagos/objeto")]
        public List<Pagos> Get([FromBody]Pagos Pago)
        {
            return Pago.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO PAGO
        /// </summary>
        /// <param name="Pago"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/pagos/agregar")]
        public DataSet Add([FromBody]Pagos Pago)
        {
            return Pago.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN PAGO
        /// </summary>
        /// <param name="Pago"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/pagos/actualizar")]
        public DataSet Update([FromBody]Pagos Pago)
        {
            return Pago.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN PAGO
        /// </summary>
        /// <param name="Pago"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/pagos/eliminar")]
        public DataSet Delete([FromBody]Pagos Pago)
        {
            Pago.IdEstatus = 2;
            return Pago.Actualizar();
        }
    }
}
