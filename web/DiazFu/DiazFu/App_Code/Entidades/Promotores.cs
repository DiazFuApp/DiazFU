using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
{
    public class Promotores
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private string _Nombre;

        public string Nombre
        {
            get { return _Nombre; }
            set { _Nombre = value; }
        }

        private string _Direccion;

        public string Direccion
        {
            get { return _Direccion; }
            set { _Direccion = value; }
        }

        private string _TelefonoCasa;

        public string TelefonoCasa
        {
            get { return _TelefonoCasa; }
            set { _TelefonoCasa = value; }
        }

        private string _TelefonoCelular;

        public string TelefonoCelular
        {
            get { return _TelefonoCelular; }
            set { _TelefonoCelular = value; }
        }

        private string _CorreoElectronico;

        public string CorreoElectronico
        {
            get { return _CorreoElectronico; }
            set { _CorreoElectronico = value; }
        }

        private DateTime? _FechaNacimiento;

        public DateTime? FechaNacimiento
        {
            get { return _FechaNacimiento; }
            set { _FechaNacimiento = value; }
        }

        private string _RFC;

        public string RFC
        {
            get { return _RFC; }
            set { _RFC = value; }
        }

        private string _CURP;

        public string CURP
        {
            get { return _CURP; }
            set { _CURP = value; }
        }

        private string _ClaveElector;

        public string ClaveElector
        {
            get { return _ClaveElector; }
            set { _ClaveElector = value; }
        }

        private string _URLFoto;

        public string URLFoto
        {
            get { return _URLFoto; }
            set { _URLFoto = value; }
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
        public Promotores()
        {

        }

        public Promotores(int IdUsuario)
        {
            IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un promotor.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un promotor.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los promotores activos.
        /// </summary>
        /// <returns>Data Set con todos los promotores activos.</returns>
        public DataSet ConsultarTodo()
        {
            Id = null;
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar un promotor según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                Nombre = Fila["Nombre"].ToString();
                Direccion = Fila["Direccion"].ToString();
                TelefonoCasa = Fila["TelefonoCasa"].ToString();
                TelefonoCelular = Fila["TelefonoCelular"].ToString();
                CorreoElectronico = Fila["CorreoElectronico"].ToString();
                FechaNacimiento = DateTime.Parse(Fila["FechaNacimiento"].ToString());
                RFC = Fila["RFC"].ToString();
                CURP = Fila["CURP"].ToString();
                ClaveElector = Fila["ClaveElector"].ToString();
                URLFoto = Fila["URLFoto"].ToString();
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
        public DataSet EjecutarSP(int Opcion)
        {
            List<SqlParameter> Parametros = new List<SqlParameter>
            {
                new SqlParameter("@Opcion", Opcion),
                new SqlParameter("@Id", Id),
                new SqlParameter("@Nombre", Nombre),
                new SqlParameter("@Direccion", Direccion),
                new SqlParameter("@TelefonoCasa", TelefonoCasa),
                new SqlParameter("@TelefonoCelular", TelefonoCelular),
                new SqlParameter("@CorreoElectronico", CorreoElectronico),
                new SqlParameter("@FechaNacimiento", FechaNacimiento),
                new SqlParameter("@RFC", RFC),
                new SqlParameter("@CURP", CURP),
                new SqlParameter("@ClaveElector", ClaveElector),
                new SqlParameter("@URLFoto", URLFoto),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPPromotores]", Parametros.ToArray());
        }
        #endregion
    }
}