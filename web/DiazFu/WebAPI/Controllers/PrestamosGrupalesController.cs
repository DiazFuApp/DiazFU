using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class PrestamosGrupalesController : ApiController
    {
        PrestamosGrupales Prestamos = new PrestamosGrupales();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/prestamosgrupales/ejemplo")]
        public PrestamosGrupales Example()
        {
            return Prestamos;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS PRESTAMOS GRUPALES
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/PrestamosGrupales")]
        public List<PrestamosGrupales> GetAll()
        {
            return Prestamos.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL PRESTAMOS GRUPALES POR EL ID
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/prestamosgrupales/{id}")]
        public List<PrestamosGrupales> Get(int id)
        {
            Prestamos.Id = id;
            return Prestamos.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL PRESTAMOS GRUPALES POR EL OBJETO
        /// </summary>
        /// <param name="Prestamo"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/prestamosgrupales/objeto")]
        public List<PrestamosGrupales> Get([FromBody]PrestamosGrupales Prestamo)
        {
            return Prestamo.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO PRESTAMO GRUPAL
        /// </summary>
        /// <param name="Prestamo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/prestamosgrupales/agregar")]
        public DataSet Add([FromBody]PrestamosGrupales Prestamo)
        {
            return Prestamo.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN PRESTAMO GRUPAL
        /// </summary>
        /// <param name="Prestamo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/prestamosgrupales/actualizar")]
        public DataSet Update([FromBody]PrestamosGrupales Prestamo)
        {
            return Prestamo.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN PRESTAMO GRUPAL
        /// </summary>
        /// <param name="Prestamo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/prestamosgrupales/eliminar")]
        public DataSet Delete([FromBody]PrestamosGrupales Prestamo)
        {
            Prestamo.IdEstatus = 2;
            return Prestamo.Actualizar();
        }
    }
}
