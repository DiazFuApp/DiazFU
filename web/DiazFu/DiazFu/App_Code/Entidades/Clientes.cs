using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
{
    public class Clientes
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

        private string _Direccion;

        public string Direccion
        {
            get { return _Direccion; }
            set { _Direccion = value; }
        }

        private DateTime? _FechaNacimiento;

        public DateTime? FechaNacimiento
        {
            get { return _FechaNacimiento; }
            set { _FechaNacimiento = value; }
        }

        private string _CorreoElectronico;

        public string CorreoElectronico
        {
            get { return _CorreoElectronico; }
            set { _CorreoElectronico = value; }
        }

        private string _URLFoto;

        public string URLFoto
        {
            get { return _URLFoto; }
            set { _URLFoto = value; }
        }

        private string _NombreEmpresa;

        public string NombreEmpresa
        {
            get { return _NombreEmpresa; }
            set { _NombreEmpresa = value; }
        }

        private string _PuestoEmpresa;

        public string PuestoEmpresa
        {
            get { return _PuestoEmpresa; }
            set { _PuestoEmpresa = value; }
        }

        private string _DireccionEmpresa;

        public string DireccionEmpresa
        {
            get { return _DireccionEmpresa; }
            set { _DireccionEmpresa = value; }
        }

        private string _HorarioEmpresa;

        public string HorarioEmpresa
        {
            get { return _HorarioEmpresa; }
            set { _HorarioEmpresa = value; }
        }

        private string _Antiguedad;

        public string Antiguedad
        {
            get { return _Antiguedad; }
            set { _Antiguedad = value; }
        }

        private string _TelefonoEmpresa;

        public string TelefonoEmpresa
        {
            get { return _TelefonoEmpresa; }
            set { _TelefonoEmpresa = value; }
        }

        private string _SueldoMensual;

        public string SueldoMensual
        {
            get { return _SueldoMensual; }
            set { _SueldoMensual = value; }
        }

        private string _NombreJefe;

        public string NombreJefe
        {
            get { return _NombreJefe; }
            set { _NombreJefe = value; }
        }

        private string _TelefonoJefe;

        public string TelefonoJefe
        {
            get { return _TelefonoJefe; }
            set { _TelefonoJefe = value; }
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
        public Clientes()
        {

        }

        public Clientes(int IdUsuario)
        {
            this.IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un cliente.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            this.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un cliente.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los clientes activos.
        /// </summary>
        /// <returns>Data Set con todos los clientes activos.</returns>
        public DataSet ConsultarTodo()
        {
            this.Id = null;
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar un cliente según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                this.Id = int.Parse(Fila["Id"].ToString());
                this.Nombre = Fila["Nombre"].ToString();
                this.TelefonoCasa = Fila["TelefonoCasa"].ToString();
                this.TelefonoCelular = Fila["TelefonoCelular"].ToString();
                this.Direccion = Fila["Direccion"].ToString();
                this.FechaNacimiento = DateTime.Parse(Fila["FechaNacimiento"].ToString());
                this.CorreoElectronico = Fila["CorreoElectronico"].ToString();
                this.URLFoto = Fila["URLFoto"].ToString();
                this.NombreEmpresa = Fila["NombreEmpresa"].ToString();
                this.PuestoEmpresa = Fila["PuestoEmpresa"].ToString();
                this.DireccionEmpresa = Fila["DireccionEmpresa"].ToString();
                this.HorarioEmpresa = Fila["HorarioEmpresa"].ToString();
                this.Antiguedad = Fila["Antiguedad"].ToString();
                this.TelefonoEmpresa = Fila["TelefonoEmpresa"].ToString();
                this.SueldoMensual = Fila["SueldoMensual"].ToString();
                this.NombreJefe = Fila["NombreJefe"].ToString();
                this.TelefonoJefe = Fila["TelefonoJefe"].ToString();
                this.IdEstatus = int.Parse(Fila["IdEstatus"].ToString());
            }
            else
            {
                this.Id = null;
            }
        }

        /// <summary>
        /// Función para ejecutar el procedimiento almacenado seleccionado.
        /// </summary>
        /// <returns>Data Set con la consulta emitida por SQL</returns>
        public DataSet EjecutarSP(int Opcion)
        {
            List<SqlParameter> Parametros = new List<SqlParameter>();
            Parametros.Add(new SqlParameter("@Opcion", Opcion));
            Parametros.Add(new SqlParameter("@Id", Id));
            Parametros.Add(new SqlParameter("@Nombre", Nombre));
            Parametros.Add(new SqlParameter("@TelefonoCasa", TelefonoCasa));
            Parametros.Add(new SqlParameter("@TelefonoCelular", TelefonoCelular));
            Parametros.Add(new SqlParameter("@Direccion", Direccion));
            Parametros.Add(new SqlParameter("@FechaNacimiento", FechaNacimiento));
            Parametros.Add(new SqlParameter("@CorreoElectronico", CorreoElectronico));
            Parametros.Add(new SqlParameter("@URLFoto", URLFoto));
            Parametros.Add(new SqlParameter("@NombreEmpresa", NombreEmpresa));
            Parametros.Add(new SqlParameter("@PuestoEmpresa", PuestoEmpresa));
            Parametros.Add(new SqlParameter("@DireccionEmpresa", DireccionEmpresa));
            Parametros.Add(new SqlParameter("@HorarioEmpresa", HorarioEmpresa));
            Parametros.Add(new SqlParameter("@Antiguedad", Antiguedad));
            Parametros.Add(new SqlParameter("@TelefonoEmpresa", TelefonoEmpresa));
            Parametros.Add(new SqlParameter("@SueldoMensual", SueldoMensual));
            Parametros.Add(new SqlParameter("@NombreJefe", NombreJefe));
            Parametros.Add(new SqlParameter("@TelefonoJefe", TelefonoJefe));
            Parametros.Add(new SqlParameter("@IdEstatus", IdEstatus));
            Parametros.Add(new SqlParameter("@IdUsuarioActual", IdUsuario));

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPClientes]", Parametros.ToArray());
        }
        #endregion
    }
}