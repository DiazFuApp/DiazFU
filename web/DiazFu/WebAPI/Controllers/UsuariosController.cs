using System.Collections.Generic;
using System.Web.Http;
using WebAPI.Models;

namespace WebAPI.Controllers
{
    public class UsuariosController : ApiController
    {
        Usuarios Usuarios = new Usuarios();

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR TODOS LOS USUARIOS
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("api/usuarios")]
        public List<Usuarios> GetAllUsuarios()
        {
            return Usuarios.ConsultarTodo();
        }

        /// <summary>
        /// FUNCIÓN PARA CONSULTAR EL USUARIO POR EL ID
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        [ActionName("Usuario")]
        [Route("api/usuarios/{id}")]
        public Usuarios GetUsuario(int id)
        {
            Usuarios.Id = id;
            Usuarios.ConsultarID();
            return Usuarios;
        }

        /// <summary>
        /// FUNCIÓN PARA LOGIN DE LA APLICACIÓN
        /// </summary>
        /// <param name="nombre"></param>
        /// <param name="contrasena"></param>
        /// <returns></returns>
        [HttpPost]
        [ActionName("Login")]
        [Route("api/usuarios/login")]
        public Usuarios GetLogin([FromBody] Usuarios UsuarioConsulta)
        {
            Usuarios.Nombre = UsuarioConsulta.Nombre;
            Usuarios.Contrasena = UsuarioConsulta.Contrasena;
            Usuarios.LogIn();
            return Usuarios;
        }
    }
}
