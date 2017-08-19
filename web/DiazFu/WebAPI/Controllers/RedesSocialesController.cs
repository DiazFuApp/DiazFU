using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class RedesSocialesController : ApiController
    {
        RedesSociales RedesSociales = new RedesSociales();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/redessociales/ejemplo")]
        public RedesSociales Example()
        {
            return RedesSociales;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LAS REDES SOCIALES
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/redessociales")]
        public List<RedesSociales> GetAll()
        {
            return RedesSociales.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR LA RED SOCIAL
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/redessociales/{id}")]
        public List<RedesSociales> Get(int id)
        {
            RedesSociales.Id = id;
            return RedesSociales.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR LA RED SOCIAL
        /// </summary>
        /// <param name="Referencia"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/redessociales/objeto")]
        public List<RedesSociales> Get([FromBody]RedesSociales Red)
        {
            return Red.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UNA NUEVA RED SOCIAL
        /// </summary>
        /// <param name="Red"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/redessociales/agregar")]
        public DataSet Add([FromBody]RedesSociales Red)
        {
            return Red.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UNA RED SOCIAL
        /// </summary>
        /// <param name="Red"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/redessociales/actualizar")]
        public DataSet Update([FromBody]RedesSociales Red)
        {
            return Red.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UNA RED SOCIAL
        /// </summary>
        /// <param name="Red"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/redessociales/eliminar")]
        public DataSet Delete([FromBody]RedesSociales Red)
        {
            Red.IdEstatus = 2;
            return Red.Actualizar();
        }
    }
}
