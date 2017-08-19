using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class ReferenciasPrestamosController : ApiController
    {
        ReferenciasPrestamos Referencia = new ReferenciasPrestamos();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/referenciasprestamos/ejemplo")]
        public ReferenciasPrestamos Example()
        {
            return Referencia;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LAS REFERENCIAS DEL PRESTAMO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/referenciasprestamos")]
        public List<ReferenciasPrestamos> GetAll()
        {
            return Referencia.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR LA REFERENCIA DEL PRESTAMO
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/referenciasprestamos/{id}")]
        public List<ReferenciasPrestamos> Get(int id)
        {
            Referencia.Id = id;
            return Referencia.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR LA REFERENCIA DEL PRESTAMO
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/referenciasprestamos/objeto")]
        public List<ReferenciasPrestamos> Get([FromBody]ReferenciasPrestamos Referencia)
        {
            return Referencia.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UNA NUEVA REFERENCIA DEL PRESTAMO
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/referenciasprestamos/agregar")]
        public DataSet Add([FromBody]ReferenciasPrestamos Referencia)
        {
            return Referencia.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UNA REFERENCIA DEL PRESTAMO
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/referenciasprestamos/actualizar")]
        public DataSet Update([FromBody]ReferenciasPrestamos Referencia)
        {
            return Referencia.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UNA REFERENCIA DEL PRESTAMO
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/referenciasprestamos/eliminar")]
        public DataSet Delete([FromBody]ReferenciasPrestamos Referencia)
        {
            Referencia.IdEstatus = 2;
            return Referencia.Actualizar();
        }
    }
}
