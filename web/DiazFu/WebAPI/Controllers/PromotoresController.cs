using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class PromotoresController : ApiController
    {
        Promotores Promotor = new Promotores();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/promotores/ejemplo")]
        public Promotores Example()
        {
            return Promotor;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS PROMOTORES
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/promotores")]
        public List<Promotores> GetAll()
        {
            return Promotor.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL PROMOTOR POR EL ID
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/promotores/{id}")]
        public Promotores Get(int id)
        {
            Promotor.Id = id;
            Promotor.ConsultarID();
            return Promotor;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL PROMOTOR POR EL NOMBRE
        /// </summary>
        /// <param name="nombre"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/promotores/nombre/{nombre}")]
        public List<Promotores> Get(string nombre)
        {
            Promotor.Nombre = nombre;
            return Promotor.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO PROMOTOR
        /// </summary>
        /// <param name="Promotor"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/promotores/agregar")]
        public DataSet Add([FromBody]Promotores Promotor)
        {
            return Promotor.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN PROMOTOR
        /// </summary>
        /// <param name="Promotor"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/promotores/actualizar")]
        public DataSet Update([FromBody]Promotores Promotor)
        {
            return Promotor.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN PROMOTOR
        /// </summary>
        /// <param name="Promotor"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/promotores/eliminar")]
        public DataSet Delete([FromBody]Promotores Promotor)
        {
            Promotor.IdEstatus = 2;
            return Promotor.Actualizar();
        }
    }
}