using System.Collections.Generic;
using System.Data;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class ClientesController : ApiController
    {
        Clientes Cliente = new Clientes();

        /// <summary>
        /// FUNCIÓN PARA OBTENER UN EJEMPLO DEL OBJETO
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/clientes/ejemplo")]
        public Clientes Example()
        {
            return Cliente;
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS CLIENTES
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/clientes")]
        public List<Clientes> GetAll()
        {
            return Cliente.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL CLIENTE
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/clientes/{id}")]
        public List<Clientes> Get(int id)
        {
            Cliente.Id = id;
            return Cliente.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR UN CLIENTE
        /// </summary>
        /// <param name="nombre"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("api/clientes/nombre/{nombre}")]
        public List<Clientes> Get(string nombre)
        {
            Cliente.Nombre = nombre;
            return Cliente.Consultar();
        }

        /// <summary>
        /// FUNCIÓN PARA AGREGAR UN NUEVO CLIENTE
        /// </summary>
        /// <param name="Cliente"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/clientes/agregar")]
        public DataSet Add([FromBody]Clientes Cliente)
        {
            return Cliente.Agregar();
        }

        /// <summary>
        /// FUNCIÓN PARA ACTUALIZAR UN CLIENTE
        /// </summary>
        /// <param name="Cliente"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/clientes/actualizar")]
        public DataSet Update([FromBody]Clientes Cliente)
        {
            return Cliente.Actualizar();
        }

        /// <summary>
        /// FUNCIÓN PARA DAR DE BAJA A UN CLIENTE
        /// </summary>
        /// <param name="Cliente"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("api/clientes/eliminar")]
        public DataSet Delete([FromBody]Clientes Cliente)
        {
            Cliente.IdEstatus = 2;
            return Cliente.Actualizar();
        }
    }
}
