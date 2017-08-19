using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class PrestamosIndividualesController : ApiController
    {
        PrestamosIndividuales Prestamos = new PrestamosIndividuales();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/prestamosindividuales/ejemplo")]
        public PrestamosIndividuales Example()
        {
            return Prestamos;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS PRESTAMOS INDIVIDUALES
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/prestamosindividuales")]
        public List<PrestamosIndividuales> GetAll()
        {
            return Prestamos.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL PRESTAMOS INDIVIDUALES POR EL ID
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/prestamosindividuales/{id}")]
        public List<PrestamosIndividuales> Get(int id)
        {
            Prestamos.Id = id;
            return Prestamos.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL PRESTAMOS INDIVIDUALES POR EL OBJETO
        /// </summary>
        /// <param name="Prestamo"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/prestamosindividuales/objeto")]
        public List<PrestamosIndividuales> Get([FromBody]PrestamosIndividuales Prestamo)
        {
            return Prestamo.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO PRESTAMO INDIVIDUAL
        /// </summary>
        /// <param name="Prestamo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/prestamosindividuales/agregar")]
        public DataSet Add([FromBody]PrestamosIndividuales Prestamo)
        {
            return Prestamo.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN PRESTAMO INDIVIDUAL
        /// </summary>
        /// <param name="Prestamo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/prestamosindividuales/actualizar")]
        public DataSet Update([FromBody]PrestamosIndividuales Prestamo)
        {
            return Prestamo.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN PRESTAMO INDIVIDUAL
        /// </summary>
        /// <param name="Prestamo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/prestamosindividuales/eliminar")]
        public DataSet Delete([FromBody]PrestamosIndividuales Prestamo)
        {
            Prestamo.IdEstatus = 2;
            return Prestamo.Actualizar();
        }
    }
}
