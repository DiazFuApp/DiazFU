using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
{
    public class ReferenciasPromotores
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

        private int? _IdTipoReferencia;

        public int? IdTipoReferencia
        {
            get { return _IdTipoReferencia; }
            set { _IdTipoReferencia = value; }
        }

        private string _Nombre;

        public string Nombre
        {
            get { return _Nombre; }
            set { _Nombre = value; }
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

        private DateTime? _FechaNacimiento;

        public DateTime? FechaNacimiento
        {
            get { return _FechaNacimiento; }
            set { _FechaNacimiento = value; }
        }

        private string _ClaveElector;

        public string ClaveElector
        {
            get { return _ClaveElector; }
            set { _ClaveElector = value; }
        }

        private string _Direccion;

        public string Direccion
        {
            get { return _Direccion; }
            set { _Direccion = value; }
        }

        private string _ReferenciaDireccion;

        public string ReferenciaDireccion
        {
            get { return _ReferenciaDireccion; }
            set { _ReferenciaDireccion = value; }
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

        private string _Parentesco;

        public string Parentesco
        {
            get { return _Parentesco; }
            set { _Parentesco = value; }
        }

        private string _URLFoto;

        public string URLFoto
        {
            get { return _URLFoto; }
            set { _URLFoto = value; }
        }

        private string _Empresa;

        public string Empresa
        {
            get { return _Empresa; }
            set { _Empresa = value; }
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

        private string _AntiguedadEmpresa;

        public string AntiguedadEmpresa
        {
            get { return _AntiguedadEmpresa; }
            set { _AntiguedadEmpresa = value; }
        }

        private string _TelefonoEmpresa;

        public string TelefonoEmpresa
        {
            get { return _TelefonoEmpresa; }
            set { _TelefonoEmpresa = value; }
        }

        private string _NombreJefe;

        public string NombreJefe
        {
            get { return _NombreJefe; }
            set { _NombreJefe = value; }
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
        public ReferenciasPromotores()
        {

        }

        public ReferenciasPromotores(int IdUsuario)
        {
            this.IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar una referencia de préstamo.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            this.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar una referencia de préstamo.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todas las referencias de préstamos activas.
        /// </summary>
        /// <returns>Data Set con todas las referencias de préstamos activas.</returns>
        public DataSet ConsultarTodo()
        {
            this.Id = null;
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar una referencia de préstamo según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                this.Id = int.Parse(Fila["Id"].ToString());
                this.IdActor = int.Parse(Fila["IdActor"].ToString());
                this.IdTipoReferencia = int.Parse(Fila["IdTipoReferencia"].ToString());
                this.Nombre = Fila["Nombre"].ToString();
                this.RFC = Fila["RFC"].ToString();
                this.CURP = Fila["CURP"].ToString();
                this.FechaNacimiento = DateTime.Parse(Fila["RFC"].ToString());
                this.ClaveElector = Fila["ClaveElector"].ToString();
                this.Direccion = Fila["Direccion"].ToString();
                this.ReferenciaDireccion = Fila["ReferenciaDireccion"].ToString();
                this.TelefonoCasa = Fila["TelefonoCasa"].ToString();
                this.TelefonoCelular = Fila["TelefonoCelular"].ToString();
                this.CorreoElectronico = Fila["CorreoElectronico"].ToString();
                this.Parentesco = Fila["Parentesco"].ToString();
                this.URLFoto = Fila["URLFoto"].ToString();
                this.Empresa = Fila["Empresa"].ToString();
                this.PuestoEmpresa = Fila["PuestoEmpresa"].ToString();
                this.DireccionEmpresa = Fila["DireccionEmpresa"].ToString();
                this.AntiguedadEmpresa = Fila["AntiguedadEmpresa"].ToString();
                this.TelefonoEmpresa = Fila["TelefonoEmpresa"].ToString();
                this.NombreJefe = Fila["NombreJefe"].ToString();
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
            Parametros.Add(new SqlParameter("@IdActor", IdActor));
            Parametros.Add(new SqlParameter("@IdTipoReferencia", IdTipoReferencia));
            Parametros.Add(new SqlParameter("@Nombre", Nombre));
            Parametros.Add(new SqlParameter("@RFC", RFC));
            Parametros.Add(new SqlParameter("@CURP", CURP));
            Parametros.Add(new SqlParameter("@FechaNacimiento", FechaNacimiento));
            Parametros.Add(new SqlParameter("@ClaveElector", ClaveElector));
            Parametros.Add(new SqlParameter("@Direccion", Direccion));
            Parametros.Add(new SqlParameter("@ReferenciaDireccion", ReferenciaDireccion));
            Parametros.Add(new SqlParameter("@TelefonoCasa", TelefonoCasa));
            Parametros.Add(new SqlParameter("@TelefonoCelular", TelefonoCelular));
            Parametros.Add(new SqlParameter("@CorreoElectronico", CorreoElectronico));
            Parametros.Add(new SqlParameter("@Parentesco", Parentesco));
            Parametros.Add(new SqlParameter("@URLFoto", URLFoto));
            Parametros.Add(new SqlParameter("@Empresa", Empresa));
            Parametros.Add(new SqlParameter("@PuestoEmpresa", PuestoEmpresa));
            Parametros.Add(new SqlParameter("@DireccionEmpresa", DireccionEmpresa));
            Parametros.Add(new SqlParameter("@AntiguedadEmpresa", AntiguedadEmpresa));
            Parametros.Add(new SqlParameter("@TelefonoEmpresa", TelefonoEmpresa));
            Parametros.Add(new SqlParameter("@NombreJefe", NombreJefe));
            Parametros.Add(new SqlParameter("@IdEstatus", IdEstatus));
            Parametros.Add(new SqlParameter("@IdUsuarioActual", IdUsuario));

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPReferenciasPromotores]", Parametros.ToArray());
        }
        #endregion
    }
}