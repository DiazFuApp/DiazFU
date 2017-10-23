﻿using SQLHelper;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using WebAPI.App_Start;

namespace WebAPI.Models
{
    public class IntegrantesGrupos
    {
        #region Propiedades
        private int? _Id;

        public int? Id
        {
            get { return _Id; }
            set { _Id = value; }
        }

        private int? _IdGrupo;

        public int? IdGrupo
        {
            get { return _IdGrupo; }
            set { _IdGrupo = value; }
        }

        private int? _IdCliente;

        public int? IdCliente
        {
            get { return _IdCliente; }
            set { _IdCliente = value; }
        }

        private string _Cliente;

        public string Cliente
        {
            get { return _Cliente; }
            set { _Cliente = value; }
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
        public IntegrantesGrupos()
        {

        }

        public IntegrantesGrupos(int IdUsuario)
        {
            IdEstatus = 1;
            this.IdUsuario = IdUsuario;
        }
        #endregion

        #region Métodos / Funciones
        /// <summary>
        /// Método para agregar un integrante al grupo.
        /// </summary>
        public DataSet Agregar()
        {
            DataSet Consulta = EjecutarSP(1);
            Id = int.Parse(Consulta.Tables[0].Rows[0]["Id"].ToString());
            return Consulta;
        }

        /// <summary>
        /// Método para actualizar un integrante al grupo.
        /// </summary>
        public DataSet Actualizar()
        {
            return EjecutarSP(2);
        }

        /// <summary>
        /// Función para consultar todos los integrantes del grupo activos.
        /// </summary>
        /// <returns>Data Set con todos los clientes activos.</returns>
        public List<IntegrantesGrupos> ConsultarTodo()
        {
            List<IntegrantesGrupos> IntegrantesGrupos = new List<IntegrantesGrupos>();
            IntegrantesGrupos integranteGrupo = new IntegrantesGrupos();
            using (DataSet Consulta = integranteGrupo.EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    IntegrantesGrupos obj = new IntegrantesGrupos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdGrupo = int.Parse(Fila["IdGrupo"].ToString()),
                        IdCliente = int.Parse(Fila["IdCliente"].ToString()),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    IntegrantesGrupos.Add(obj);
                }
            }
            return IntegrantesGrupos;
        }

        public List<IntegrantesGrupos> ConsultarIDGrupo()
        {
            List<IntegrantesGrupos> IntegrantesGrupos = new List<IntegrantesGrupos>();
            using (DataSet Consulta = EjecutarSP(3))
            {
                foreach (DataRow Fila in Consulta.Tables[0].Rows)
                {
                    IntegrantesGrupos obj = new IntegrantesGrupos
                    {
                        Id = int.Parse(Fila["Id"].ToString()),
                        IdGrupo = int.Parse(Fila["IdGrupo"].ToString()),
                        IdCliente = int.Parse(Fila["IdCliente"].ToString()),
                        Cliente = Fila["Cliente"].ToString(),
                        IdEstatus = int.Parse(Fila["IdEstatus"].ToString())
                    };
                    IntegrantesGrupos.Add(obj);
                }
            }
            return IntegrantesGrupos;
        }

        /// <summary>
        /// Método para consultar un integrante del grupo según su ID.
        /// </summary>
        public void ConsultarID()
        {
            DataSet Consulta = new DataSet();
            Consulta = EjecutarSP(3);
            if (Consulta.Tables[0].Rows.Count > 0)
            {
                DataRow Fila = Consulta.Tables[0].Rows[0];
                Id = int.Parse(Fila["Id"].ToString());
                IdGrupo = int.Parse(Fila["IdGrupo"].ToString());
                IdCliente = int.Parse(Fila["IdCliente"].ToString());
                Cliente = Fila["Cliente"].ToString();
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
                new SqlParameter("@IdGrupo", IdGrupo),
                new SqlParameter("@IdCliente", IdCliente),
                new SqlParameter("@IdEstatus", IdEstatus),
                new SqlParameter("@IdUsuarioActual", IdUsuario)
            };

            return SqlHelper.ExecuteDataset(Conexion.CadenaConexion(), "[datos].[SPIntegrantesGrupos]", Parametros.ToArray());
        }
        #endregion
    }
}