using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;

namespace DiazFu.App_Code.Entidades
{
    public class PrestamosIndividuales
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdCliente;

        public int? IdCliente
        {
            get { return _IdCliente; }
            set { _IdCliente = value; }
        }

        private string _Motivo;

        public string Motivo
        {
            get { return _Motivo; }
            set { _Motivo = value; }
        }

        private float _CantidadSolicitada;

        public float CantidadSolicitada
        {
            get { return _CantidadSolicitada; }
            set { _CantidadSolicitada = value; }
        }

        private float _Interes;

        public float Interes
        {
            get { return _Interes; }
            set { _Interes = value; }
        }

        private DateTime? _FechaEntrega;

        public DateTime? FechaEntrega
        {
            get { return _FechaEntrega; }
            set { _FechaEntrega = value; }
        }

        private string _Observaciones;

        public string Observaciones
        {
            get { return _Observaciones; }
            set { _Observaciones = value; }
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
        public PrestamosIndividuales()
        {

        }

        public PrestamosIndividuales(int IdUsuario)
        {
            this.IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un préstamo individual.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            this.Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un préstamo individual..
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los préstamos individuales activos.
        /// </summary>
        /// <returns>Data Set con todos los clientes activos.</returns>
        public DataSet ConsultarTodo()
        {
            this.Id = null;
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar un préstamo individual según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                this.Id = int.Parse(Fila["Id"].ToString());
                this.IdCliente = int.Parse(Fila["IdCliente"].ToString());
                this.Motivo = Fila["Motivo"].ToString();
                this.CantidadSolicitada = float.Parse(Fila["CantidadSolicitada"].ToString());
                this.Interes = float.Parse(Fila["Interes"].ToString());
                this.FechaEntrega = DateTime.Parse(Fila["FechaEntrega"].ToString());
                this.Observaciones = Fila["Observaciones"].ToString();
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
            Parametros.Add(new SqlParameter("@IdCliente", IdCliente));
            Parametros.Add(new SqlParameter("@Motivo", Motivo));
            Parametros.Add(new SqlParameter("@CantidadSolicitada", CantidadSolicitada));
            Parametros.Add(new SqlParameter("@Interes", Interes));
            Parametros.Add(new SqlParameter("@FechaEntrega", FechaEntrega));
            Parametros.Add(new SqlParameter("@Observaciones", Observaciones));
            Parametros.Add(new SqlParameter("@IdEstatus", IdEstatus));
            Parametros.Add(new SqlParameter("@IdUsuarioActual", IdUsuario));

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPPrestamosIndividuales]", Parametros.ToArray());
        }
        #endregion
    }
}