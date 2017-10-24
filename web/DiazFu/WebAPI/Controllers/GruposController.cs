using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class GruposController : ApiController
    {
        Grupos Grupo = new Grupos();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/grupos/ejemplo")]
        public Grupos Example()
        {
            return Grupo;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS GRUPOS
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/grupos")]
        public List<Grupos> GetAll()
        {
            return Grupo.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL GRUPO POR EL ID
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/grupos/{id}")]
        public Grupos Get(int id)
        {
            Grupo.Id = id;
            Grupo.ConsultarID();
            return Grupo;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL GRUPO POR EL NOMBRE
        /// </summary>
        /// <param name="nombre"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/grupos/nombre/{nombre}")]
        public List<Grupos> Get(string nombre)
        {
            Grupo.Nombre = nombre;
            return Grupo.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL GRUPO POR EL OBJETO
        /// </summary>
        /// <param name="nombre"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/grupos/objeto")]
        public List<Grupos> Get([FromBody]Grupos Grupo)
        {
            return Grupo.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO GRUPO
        /// </summary>
        /// <param name="Grupo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/grupos/agregar")]
        public Grupos Add([FromBody]Grupos Grupo)
        {
            Grupo.Agregar();
            return Grupo;
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN GRUPO
        /// </summary>
        /// <param name="Grupo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/grupos/actualizar")]
        public Grupos Update([FromBody]Grupos Grupo)
        {
            Grupo.Actualizar();
            return Grupo;
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN PROMOTOR
        /// </summary>
        /// <param name="Grupo"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/grupos/eliminar")]
        public Grupos Delete([FromBody]Grupos Grupo)
        {
            Grupo.IdEstatus = 2;
            Grupo.Actualizar();
            return Grupo;
        }
    }
}
