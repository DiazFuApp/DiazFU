using SQLHelper;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class Usuarios
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdActor;

        public int? IdActor
        {
            get { return _IdActor; }
            set { _IdActor = value; }
        }

        private int? _IdTipoActor;

        public int? IdTipoActor
        {
            get { return _IdTipoActor; }
            set { _IdTipoActor = value; }
        }

        private string _Nombre;

        public string Nombre
        {
            get { return _Nombre; }
            set { _Nombre = value; }
        }

        private string _Contrasena;

        public string Contrasena
        {
            get { return _Contrasena; }
            set { _Contrasena = value; }
        }

        private int _IdEstatus;

        public int IdEstatus
        {
            get { return _IdEstatus; }
            set { _IdEstatus = value; }
        }

        private int _IdUsuario;

        public int IdUsuario
        {
            get { return _IdUsuario; }
            set { _IdUsuario = value; }
        }

        #endregion

        #region Constructor
        public Usuarios() { }

        public Usuarios(int IdUsuario)
        {
            this.IdUsuario = IdUsuario;
            IdEstatus = 1;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un usuario.
        /// </summary>
        public DataSet Agregar()
        {
            return EjecutarSP(1);
        }

        /// <summary>
        /// Método para actualizar un usuario.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los usuarios activos.
        /// </summary>
        /// <returns>Data Set con todos los usuarios activos.</returns>
        public List<Usuarios> ConsultarTodo()
        {
            List<Usuarios> Usuarios = new List<Usuarios>();
            Usuarios Usuario = new Usuarios();
            using (DataSet Consulta = Usuario.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    Usuarios user = new Usuarios
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdActor = int.Parse(Fila["IdActor"].ToString()),
                        IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString()),
                        Nombre = Fila["Nombre"].ToString(),
                        Contrasena = Fila["Contrasena"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    Usuarios.Add(user);
                }
            }
            return Usuarios;
        }

        /// <summary>
        /// Método para consultar un usuario según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                IdActor = int.Parse(Fila["IdActor"].ToString());
                IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                Nombre = Fila["Nombre"].ToString();
                Contrasena = Fila["Contrasena"].ToString();
                IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                Id = null;
            }
        }

        /// <summary>
        /// Método para consultar un usuario según su ID.
        /// </summary>
        public void LogIn()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(4);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                IdActor = int.Parse(Fila["IdActor"].ToString());
                IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                Nombre = Fila["Nombre"].ToString();
                Contrasena = Fila["Contrasena"].ToString();
                IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                Id = null;
            }
        }

        /// <summary>
        /// Función para ejecutar el procedimiento almacenado seleccionado.
        /// </summary>
        /// <returns>Data Set con la consulta emitida por SQL</returns>
        public System.Data.DataSet EjecutarSP(int Opcion)
        {
            List<SqlParameter> Parametros = new List<SqlParameter>
            {
                new SqlParameter("@Opcion", Opcion),
                new SqlParameter("@Id", Id),
                new SqlParameter("@IdActor", IdActor),
                new SqlParameter("@IdTipoActor", IdTipoActor),
                new SqlParameter("@Nombre", Nombre),
                new SqlParameter("@Contrasena", Contrasena),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[seguridad].[SPUsuarios]", Parametros.ToArray());
        }
        #endregion
    }
}