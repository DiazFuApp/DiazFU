DELETE FROM datos.Actividades
DELETE FROM datos.Clientes
DELETE FROM datos.Comisiones
DELETE FROM datos.Documentos
DELETE FROM datos.Grupos
DELETE FROM datos.IntegrantesGrupos
DELETE FROM datos.Pagos
DELETE FROM datos.PrestamosGrupales
DELETE FROM datos.PrestamosIndividuales
DELETE FROM datos.Promotores
DELETE FROM datos.RedesSociales
DELETE FROM datos.ReferenciasPrestamos
DELETE FROM datos.ReferenciasPromotores
DELETE FROM seguridad.Log
DELETE FROM seguridad.Usuarios WHERE IdTipoActor != 1