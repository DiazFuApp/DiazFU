using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class ActividadesController : ApiController
    {
        Actividades Actividades = new Actividades();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/actividades/ejemplo")]
        public Actividades Example()
        {
            return Actividades;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LAS ACTIVIDADES
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/actividades")]
        public List<Actividades> GetAll()
        {
            return Actividades.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR LA ACTIVIDAD POR EL ID
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/actividades/{id}")]
        public List<Actividades> Get(int id)
        {
            Actividades.Id = id;
            return Actividades.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR LA ACTIVIDAD POR EL TITULO
        /// </summary>
        /// <param name="titulo"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/actividades/titulo/{titulo}")]
        public List<Actividades> Get(string titulo)
        {
            Actividades.Titulo = titulo;
            return Actividades.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO PAGO
        /// </summary>
        /// <param name="Pago"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/actividades/agregar")]
        public DataSet Add([FromBody]Actividades Pago)
        {
            return Pago.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN PAGO
        /// </summary>
        /// <param name="Pago"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/actividades/actualizar")]
        public DataSet Update([FromBody]Actividades Pago)
        {
            return Pago.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN PAGO
        /// </summary>
        /// <param name="Pago"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/actividades/eliminar")]
        public DataSet Delete([FromBody]Actividades Pago)
        {
            Pago.IdEstatus = 2;
            return Pago.Actualizar();
        }
    }
}
