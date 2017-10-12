﻿using DiazFu.App_Code.Utilerias;
using SQLHelper;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace DiazFu.App_Code.Entidades
{
    public class Documentos
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdTipoDocumento;

        public int? IdTipoDocumento
        {
            get { return _IdTipoDocumento; }
            set { _IdTipoDocumento = value; }
        }

        private int? _IdTipoActor;

        public int? IdTipoActor
        {
            get { return _IdTipoActor; }
            set { _IdTipoActor = value; }
        }

        private int? _IdActor;

        public int? IdActor
        {
            get { return _IdActor; }
            set { _IdActor = value; }
        }

        private string _URLDocumento;

        public string URLDocumento
        {
            get { return _URLDocumento; }
            set { _URLDocumento = value; }
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
        public Documentos()
        {

        }

        public Documentos(int IdUsuario)
        {
            this.IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un documento.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un documento.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los documentos activos.
        /// </summary>
        /// <returns>Data Set con todos los documentos activos.</returns>
        public DataSet ConsultarTodo()
        {
            return EjecutarSP(3);
        }

        /// <summary>
        /// Método para consultar un documento según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                IdTipoDocumento = int.Parse(Fila["IdTipoDocumento"].ToString());
                IdTipoActor = int.Parse(Fila["IdTipoActor"].ToString());
                IdActor = int.Parse(Fila["IdActor"].ToString());
                URLDocumento = Fila["URLDocumento"].ToString();
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
                new SqlParameter("@IdTipoDocumento", IdTipoDocumento),
                new SqlParameter("@IdActor", IdActor),
                new SqlParameter("@IdTipoActor", IdTipoActor),
                new SqlParameter("@URLDocumento", URLDocumento),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPDocumentos]", Parametros.ToArray());
        }
        #endregion
    }
}