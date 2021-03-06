USE [master]
GO
/****** Object:  Database [diazfu]    Script Date: 12/10/2017 10:33:54 a. m. ******/
CREATE DATABASE [diazfu]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'diazfu', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SKILLCODER\MSSQL\DATA\diazfu.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'diazfu_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SKILLCODER\MSSQL\DATA\diazfu_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [diazfu] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [diazfu].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [diazfu] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [diazfu] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [diazfu] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [diazfu] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [diazfu] SET ARITHABORT OFF 
GO
ALTER DATABASE [diazfu] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [diazfu] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [diazfu] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [diazfu] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [diazfu] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [diazfu] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [diazfu] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [diazfu] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [diazfu] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [diazfu] SET  ENABLE_BROKER 
GO
ALTER DATABASE [diazfu] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [diazfu] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [diazfu] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [diazfu] SET ALLOW_SNAPSHOT_ISOLATION ON 
GO
ALTER DATABASE [diazfu] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [diazfu] SET READ_COMMITTED_SNAPSHOT ON 
GO
ALTER DATABASE [diazfu] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [diazfu] SET RECOVERY FULL 
GO
ALTER DATABASE [diazfu] SET  MULTI_USER 
GO
ALTER DATABASE [diazfu] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [diazfu] SET DB_CHAINING OFF 
GO
ALTER DATABASE [diazfu] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [diazfu] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [diazfu] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'diazfu', N'ON'
GO
ALTER DATABASE [diazfu] SET QUERY_STORE = ON
GO
ALTER DATABASE [diazfu] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 7), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 10, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO)
GO
USE [diazfu]
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [diazfu]
GO
/****** Object:  Schema [catalogos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
CREATE SCHEMA [catalogos]
GO
/****** Object:  Schema [datos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
CREATE SCHEMA [datos]
GO
/****** Object:  Schema [seguridad]    Script Date: 12/10/2017 10:33:55 a. m. ******/
CREATE SCHEMA [seguridad]
GO
/****** Object:  Table [catalogos].[Estatus]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [catalogos].[Estatus](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Estatus] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [catalogos].[Prioridades]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [catalogos].[Prioridades](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModifica] [datetime] NULL,
 CONSTRAINT [PK_Prioridades] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [catalogos].[TiposActores]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [catalogos].[TiposActores](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_TiposActores] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [catalogos].[TiposDocumentos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [catalogos].[TiposDocumentos](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_TiposDocumentos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [catalogos].[TiposPrestamos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [catalogos].[TiposPrestamos](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[IdEstatus] [int] NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_TiposPrestamos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [catalogos].[TiposRedesSociales]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [catalogos].[TiposRedesSociales](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModifica] [datetime] NULL,
 CONSTRAINT [PK_TiposRedesSociales] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [catalogos].[TiposReferencias]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [catalogos].[TiposReferencias](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_TiposReferencias] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [datos].[Actividades]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[Actividades](
	[Id] [int] NOT NULL,
	[IdPromotor] [int] NOT NULL,
	[Titulo] [varchar](100) NOT NULL,
	[Descripcion] [varchar](max) NOT NULL,
	[IdPrioridad] [int] NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Actividades] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [datos].[Administradores]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[Administradores](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Administradores] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [datos].[Clientes]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[Clientes](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[TelefonoCasa] [varchar](50) NULL,
	[TelefonoCelular] [varchar](50) NULL,
	[Direccion] [varchar](max) NULL,
	[FechaNacimiento] [datetime] NULL,
	[CorreoElectronico] [varchar](50) NULL,
	[URLFoto] [varchar](max) NULL,
	[NombreEmpresa] [varchar](100) NULL,
	[PuestoEmpresa] [varchar](50) NULL,
	[DireccionEmpresa] [varchar](max) NULL,
	[HorarioEmpresa] [varchar](50) NULL,
	[Antiguedad] [varchar](50) NULL,
	[TelefonoEmpresa] [varchar](50) NULL,
	[SueldoMensual] [varchar](50) NULL,
	[NombreJefe] [varchar](100) NULL,
	[TelefonoJefe] [varchar](50) NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Clientes] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [datos].[Comisiones]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[Comisiones](
	[Id] [int] NOT NULL,
	[IdPromotor] [int] NOT NULL,
	[Comision] [money] NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Comisiones] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [datos].[Documentos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[Documentos](
	[Id] [int] NOT NULL,
	[IdTipoDocumento] [int] NOT NULL,
	[IdActor] [int] NOT NULL,
	[IdTipoActor] [int] NOT NULL,
	[URLDocumento] [varchar](max) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Documentos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [datos].[Grupos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[Grupos](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[IdClienteResponsable] [int] NOT NULL,
	[IdPromotor] [int] NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Grupos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [datos].[IntegrantesGrupos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[IntegrantesGrupos](
	[Id] [int] NOT NULL,
	[IdGrupo] [int] NOT NULL,
	[IdCliente] [int] NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_IntegrantesGrupos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [datos].[Pagos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[Pagos](
	[Id] [int] NOT NULL,
	[IdPrestamo] [int] NOT NULL,
	[IdCliente] [int] NOT NULL,
	[IdTipoPrestamo] [int] NOT NULL,
	[Monto] [nchar](8) NOT NULL,
	[Plazo] [varchar](50) NULL,
	[TipoPago] [varchar](50) NULL,
	[FechaProgramada] [datetime] NULL,
	[FechaPago] [datetime] NULL,
	[Morosidad] [money] NULL,
	[Descripcion] [varchar](max) NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCrea] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModifica] [datetime] NULL,
 CONSTRAINT [PK_Pagos] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [datos].[PrestamosGrupales]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[PrestamosGrupales](
	[Id] [int] NOT NULL,
	[IdGrupo] [int] NOT NULL,
	[Motivo] [varchar](max) NOT NULL,
	[CantidadSolicitada] [money] NOT NULL,
	[CantidadOtorgada] [money] NULL,
	[Interes] [float] NULL,
	[Garantia] [varchar](100) NOT NULL,
	[Anticipo] [money] NULL,
	[FechaEntrega] [datetime] NULL,
	[Observaciones] [varchar](max) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_PrestamosGrupales] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [datos].[PrestamosIndividuales]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[PrestamosIndividuales](
	[Id] [int] NOT NULL,
	[IdCliente] [int] NOT NULL,
	[Motivo] [varchar](max) NOT NULL,
	[CantidadSolicitada] [money] NOT NULL,
	[CantidadOtorgada] [money] NULL,
	[Interes] [float] NULL,
	[Garantia] [varchar](100) NOT NULL,
	[Anticipo] [money] NULL,
	[FechaEntrega] [datetime] NULL,
	[Observaciones] [varchar](max) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_PrestamosIndividuales] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [datos].[Promotores]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[Promotores](
	[Id] [int] NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[Direccion] [varchar](max) NULL,
	[TelefonoCasa] [varchar](50) NULL,
	[TelefonoCelular] [varchar](50) NULL,
	[CorreoElectronico] [varchar](50) NULL,
	[FechaNacimiento] [datetime] NULL,
	[RFC] [varchar](50) NULL,
	[CURP] [varchar](50) NULL,
	[ClaveElector] [varchar](50) NULL,
	[URLFoto] [varchar](max) NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Promotores] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [datos].[RedesSociales]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[RedesSociales](
	[Id] [int] NOT NULL,
	[IdTipoRedSocial] [int] NOT NULL,
	[IdActor] [int] NOT NULL,
	[IdTipoActor] [int] NOT NULL,
	[URL] [varchar](100) NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_RedesSociales] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [datos].[ReferenciasPrestamos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[ReferenciasPrestamos](
	[Id] [int] NOT NULL,
	[IdPrestamo] [int] NOT NULL,
	[IdTipoPrestamo] [int] NOT NULL,
	[IdTipoReferencia] [int] NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[RFC] [varchar](50) NULL,
	[CURP] [varchar](50) NULL,
	[FechaNacimiento] [datetime] NULL,
	[ClaveElector] [varchar](50) NULL,
	[Direccion] [varchar](max) NULL,
	[ReferenciaDireccion] [varchar](max) NULL,
	[TelefonoCasa] [varchar](50) NULL,
	[TelefonoCelular] [varchar](50) NULL,
	[CorreoElectronico] [varchar](50) NULL,
	[Parentesco] [varchar](50) NULL,
	[URLFoto] [varchar](max) NULL,
	[Empresa] [varchar](50) NULL,
	[PuestoEmpresa] [varchar](50) NULL,
	[DireccionEmpresa] [varchar](max) NULL,
	[AntiguedadEmpresa] [varchar](50) NULL,
	[TelefonoEmpresa] [varchar](50) NULL,
	[NombreJefe] [varchar](100) NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [datos].[ReferenciasPromotores]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [datos].[ReferenciasPromotores](
	[Id] [int] NOT NULL,
	[IdActor] [int] NOT NULL,
	[IdTipoReferencia] [int] NOT NULL,
	[Nombre] [varchar](100) NOT NULL,
	[RFC] [varchar](50) NULL,
	[CURP] [varchar](50) NULL,
	[FechaNacimiento] [datetime] NULL,
	[ClaveElector] [varchar](50) NULL,
	[Direccion] [varchar](max) NULL,
	[TelefonoCasa] [varchar](50) NULL,
	[TelefonoCelular] [varchar](50) NULL,
	[CorreoElectronico] [varchar](50) NULL,
	[URLFoto] [varchar](max) NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Referencias] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [seguridad].[Log]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [seguridad].[Log](
	[Id] [int] NOT NULL,
	[Descripcion] [varchar](max) NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
 CONSTRAINT [PK_Log] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [seguridad].[Usuarios]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [seguridad].[Usuarios](
	[Id] [int] NOT NULL,
	[IdActor] [int] NOT NULL,
	[IdTipoActor] [int] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Contrasena] [varchar](50) NOT NULL,
	[IdEstatus] [int] NOT NULL,
	[IdUsuarioCrea] [int] NOT NULL,
	[FechaCreacion] [datetime] NOT NULL,
	[IdUsuarioModifica] [int] NULL,
	[FechaModificacion] [datetime] NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  StoredProcedure [datos].[SPActividades]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ
-- Create date: 07/08/2017
-- Description:	PRODECIMIENTO ALMACENADO CORRESPONDIENTE A LAS ACTIVIDADES
-- =============================================
CREATE PROCEDURE [datos].[SPActividades]
	@Opcion INT,
	@Id INT,
	@IdPromotor INT,
	@Titulo VARCHAR(100),
	@Descripcion VARCHAR(MAX),
	@IdPrioridad INT,
	@IdEstatus INT,
	@IdUsuarioActual INT

AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DE LA ACTIVIDAD
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[Actividades])
			INSERT INTO [datos].[Actividades]
					   ([Id]
					   ,[IdPromotor]
					   ,[Titulo]
					   ,[Descripcion]
					   ,[IdPrioridad]
					   ,[IdEstatus]
					   ,[IdUsuarioCrea]
					   ,[FechaCreacion])
				 VALUES
					   (@Id
					   ,@IdPromotor
					   ,@Titulo
					   ,@Descripcion
					   ,@IdPrioridad
					   ,1
					   ,@IdUsuarioActual
					   ,GETDATE())

			SET @log = CONCAT('Actividad ', @Id, ' creada correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Actividad creada.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar actividad ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear la actividad.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DE LA ACTIVIDAD
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[Actividades]
			   SET [IdPromotor] = ISNULL(@IdPromotor, [IdPromotor])
				  ,[Titulo] = ISNULL(@Titulo, [Titulo])
				  ,[Descripcion] = ISNULL(@Descripcion, [Descripcion])
				  ,[IdPrioridad] = ISNULL(@IdPrioridad, [IdPrioridad])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Actividad ', @Id, ' actualizada correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Actividad actualizada.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar actividad ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar la actividad.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	
	-------------------------------------------------------------------------------------------
	--CONSULTAR LA ACTIVIDAD
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Actividades].[Id]
			  ,[Actividades].[IdPromotor]
			  ,[Promotores].[Nombre] AS Promotor
			  ,[Actividades].[Titulo]
			  ,[Actividades].[Descripcion]
			  ,[Actividades].[IdPrioridad]
			  ,[Prioridades].[Nombre] AS Prioridad
			  ,[Actividades].[IdEstatus]
			  ,[Estatus].[Nombre] AS Estatus
			  ,[Actividades].[IdUsuarioCrea]
			  ,[Actividades].[FechaCreacion]
			  ,[Actividades].[IdUsuarioModifica]
			  ,[Actividades].[FechaModificacion]
		  FROM [datos].[Actividades] AS [Actividades] 
		  INNER JOIN [datos].[Promotores] AS [Promotores] ON [Actividades].[IdPromotor] = [Promotores].[Id]
		  INNER JOIN [catalogos].[Prioridades] AS [Prioridades] ON [Actividades].[IdPrioridad] = [Prioridades].[Id]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Actividades].[IdEstatus] = [Estatus].[IdEstatus]
		  WHERE [Actividades].[Id] = ISNULL(@Id, [Actividades].[Id])
		  AND [Actividades].[IdEstatus] != 2
		  AND [Actividades].[Titulo] LIKE '%' + ISNULL(@Titulo, [Actividades].[Titulo]) + '%'
		  AND [Actividades].[IdPromotor] = ISNULL(@IdPromotor, [Actividades].[IdPromotor])
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPClientes]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GOMEZ LEYVA
-- Create date: 08/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS CLIENTES
-- =============================================
CREATE PROCEDURE [datos].[SPClientes]
	@Opcion INT,
	@Id INT,
	@Nombre VARCHAR(100),
	@TelefonoCasa VARCHAR(50),
	@TelefonoCelular VARCHAR(50),
	@Direccion VARCHAR(MAX),
	@FechaNacimiento DATETIME,
	@CorreoElectronico VARCHAR(50),
	@URLFoto VARCHAR(MAX),
	@NombreEmpresa VARCHAR(100),
	@PuestoEmpresa VARCHAR(50),
	@DireccionEmpresa VARCHAR(MAX),
	@HorarioEmpresa VARCHAR(50),
	@Antiguedad VARCHAR(50),
	@TelefonoEmpresa VARCHAR(50),
	@SueldoMensual VARCHAR(50),
	@NombreJefe VARCHAR(100),
	@TelefonoJefe VARCHAR(50),
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL CLIENTE
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[Clientes])
			INSERT INTO [datos].[Clientes]
					   ([Id]
					   ,[Nombre]
					   ,[TelefonoCasa]
					   ,[TelefonoCelular]
					   ,[Direccion]
					   ,[FechaNacimiento]
					   ,[CorreoElectronico]
					   ,[URLFoto]
					   ,[NombreEmpresa]
					   ,[PuestoEmpresa]
					   ,[DireccionEmpresa]
					   ,[HorarioEmpresa]
					   ,[Antiguedad]
					   ,[TelefonoEmpresa]
					   ,[SueldoMensual]
					   ,[NombreJefe]
					   ,[TelefonoJefe]
					   ,[IdEstatus]
					   ,[IdUsuarioCrea]
					   ,[FechaCreacion])
				 VALUES
					   (@Id
					   ,@Nombre
					   ,@TelefonoCasa
					   ,@TelefonoCelular
					   ,@Direccion
					   ,@FechaNacimiento
					   ,@CorreoElectronico
					   ,@URLFoto
					   ,@NombreEmpresa
					   ,@PuestoEmpresa
					   ,@DireccionEmpresa
					   ,@HorarioEmpresa
					   ,@Antiguedad
					   ,@TelefonoEmpresa
					   ,@SueldoMensual
					   ,@NombreJefe
					   ,@TelefonoJefe
					   ,3
					   ,@IdUsuarioActual
					   ,GETDATE())

			SET @log = CONCAT('Cliente ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Cliente creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar cliente ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el cliente.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL CLIENTE
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[Clientes]
			   SET [Nombre] = ISNULL(@Nombre, [Nombre])
				  ,[TelefonoCasa] = ISNULL(@TelefonoCasa, [TelefonoCasa])
				  ,[TelefonoCelular] = ISNULL(@TelefonoCelular, [TelefonoCelular])
				  ,[Direccion] = ISNULL(@Direccion, [Direccion])
				  ,[FechaNacimiento] = ISNULL(@FechaNacimiento, [FechaNacimiento])
				  ,[CorreoElectronico] = ISNULL(@CorreoElectronico, [CorreoElectronico])
				  ,[URLFoto] = ISNULL(@URLFoto, [URLFoto])
				  ,[NombreEmpresa] = ISNULL(@NombreEmpresa, [NombreEmpresa])
				  ,[PuestoEmpresa] = ISNULL(@PuestoEmpresa, [PuestoEmpresa])
				  ,[DireccionEmpresa] = ISNULL(@DireccionEmpresa, [DireccionEmpresa])
				  ,[HorarioEmpresa] = ISNULL(@HorarioEmpresa, [HorarioEmpresa])
				  ,[Antiguedad] = ISNULL(@Antiguedad, [Antiguedad])
				  ,[TelefonoEmpresa] = ISNULL(@TelefonoEmpresa, [TelefonoEmpresa])
				  ,[SueldoMensual] = ISNULL(@SueldoMensual, [SueldoMensual])
				  ,[NombreJefe] = ISNULL(@NombreJefe, [NombreJefe])
				  ,[TelefonoJefe] = ISNULL(@TelefonoJefe, [TelefonoJefe])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Cliente ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Cliente actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar cliente ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar el cliente.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	
	-------------------------------------------------------------------------------------------
	--CONSULTAR EL CLIENTE
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Cliente].[Id]
			  ,[Cliente].[Nombre]
			  ,[Cliente].[TelefonoCasa]
			  ,[Cliente].[TelefonoCelular]
			  ,[Cliente].[Direccion]
			  ,[Cliente].[FechaNacimiento]
			  ,[Cliente].[CorreoElectronico]
			  ,[Cliente].[URLFoto]
			  ,[Cliente].[NombreEmpresa]
			  ,[Cliente].[PuestoEmpresa]
			  ,[Cliente].[DireccionEmpresa]
			  ,[Cliente].[HorarioEmpresa]
			  ,[Cliente].[Antiguedad]
			  ,[Cliente].[TelefonoEmpresa]
			  ,[Cliente].[SueldoMensual]
			  ,[Cliente].[NombreJefe]
			  ,[Cliente].[TelefonoJefe]
			  ,[Cliente].[IdEstatus]
			  ,[Estatus].[Nombre] AS Estatus
			  ,[Cliente].[IdUsuarioCrea]
			  ,[Cliente].[FechaCreacion]
			  ,[Cliente].[IdUsuarioModifica]
			  ,[Cliente].[FechaModificacion]
		  FROM [datos].[Clientes] AS [Cliente]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Cliente].[IdEstatus]
		  WHERE [Cliente].[Id] = ISNULL(@Id, [Cliente].[Id])
		  AND [Cliente].[IdEstatus] != 2
		  AND [Cliente].[Nombre] LIKE '%' + ISNULL(@Nombre, [Cliente].[Nombre]) + '%'
	END
	
	-------------------------------------------------------------------------------------------
	--CONSULTAR CLIENTES AUTORIZADOS
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 4)
	BEGIN
		SELECT [Cliente].[Id]
			  ,[Cliente].[Nombre]
			  ,[Cliente].[TelefonoCasa]
			  ,[Cliente].[TelefonoCelular]
			  ,[Cliente].[Direccion]
			  ,[Cliente].[FechaNacimiento]
			  ,[Cliente].[CorreoElectronico]
			  ,[Cliente].[URLFoto]
			  ,[Cliente].[NombreEmpresa]
			  ,[Cliente].[PuestoEmpresa]
			  ,[Cliente].[DireccionEmpresa]
			  ,[Cliente].[HorarioEmpresa]
			  ,[Cliente].[Antiguedad]
			  ,[Cliente].[TelefonoEmpresa]
			  ,[Cliente].[SueldoMensual]
			  ,[Cliente].[NombreJefe]
			  ,[Cliente].[TelefonoJefe]
			  ,[Cliente].[IdEstatus]
			  ,[Estatus].[Nombre] AS Estatus
			  ,[Cliente].[IdUsuarioCrea]
			  ,[Cliente].[FechaCreacion]
			  ,[Cliente].[IdUsuarioModifica]
			  ,[Cliente].[FechaModificacion]
		  FROM [datos].[Clientes] AS [Cliente]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Cliente].[IdEstatus]
		  WHERE [Cliente].[Id] = ISNULL(@Id, [Cliente].[Id])
		  AND [Cliente].[IdEstatus] = 4
		  AND [Cliente].[Nombre] LIKE '%' + ISNULL(@Nombre, [Cliente].[Nombre]) + '%'
	END
	
	-------------------------------------------------------------------------------------------
	--CONSULTAR CLIENTES DISPONIBLES PARA GRUPOS
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 5)
	BEGIN
		SELECT [Cliente].[Id]
			  ,[Cliente].[Nombre]
			  ,[Cliente].[TelefonoCasa]
			  ,[Cliente].[TelefonoCelular]
			  ,[Cliente].[Direccion]
			  ,[Cliente].[FechaNacimiento]
			  ,[Cliente].[CorreoElectronico]
			  ,[Cliente].[URLFoto]
			  ,[Cliente].[NombreEmpresa]
			  ,[Cliente].[PuestoEmpresa]
			  ,[Cliente].[DireccionEmpresa]
			  ,[Cliente].[HorarioEmpresa]
			  ,[Cliente].[Antiguedad]
			  ,[Cliente].[TelefonoEmpresa]
			  ,[Cliente].[SueldoMensual]
			  ,[Cliente].[NombreJefe]
			  ,[Cliente].[TelefonoJefe]
			  ,[Cliente].[IdEstatus]
			  ,[Estatus].[Nombre] AS Estatus
			  ,[Cliente].[IdUsuarioCrea]
			  ,[Cliente].[FechaCreacion]
			  ,[Cliente].[IdUsuarioModifica]
			  ,[Cliente].[FechaModificacion]
		  FROM [datos].[Clientes] AS [Cliente]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Cliente].[IdEstatus]
		  WHERE [Cliente].[Id] = ISNULL(@Id, [Cliente].[Id])
		  AND [Cliente].[IdEstatus] = 4
		  AND [Cliente].[Nombre] LIKE '%' + ISNULL(@Nombre, [Cliente].[Nombre]) + '%'
		  AND [Cliente].[Id] NOT IN (SELECT idCliente FROM datos.IntegrantesGrupos WHERE IdEstatus = 1)
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPComisiones]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 08/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LAS COMISIONES
-- =============================================
CREATE PROCEDURE [datos].[SPComisiones]
	@Opcion INT,
	@Id INT,
	@IdPromotor INT,
	@Comision MONEY,
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DE LA COMISION
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[Comisiones])
			INSERT INTO [datos].[Comisiones]
					   ([Id]
					   ,[IdPromotor]
					   ,[Comision]
					   ,[IdEstatus]
					   ,[IdUsuarioCrea]
					   ,[FechaCreacion])
				 VALUES
					   (@Id
					   ,@IdPromotor
					   ,@Comision
					   ,1
					   ,@IdUsuarioActual
					   ,GETDATE())

				SET @log = CONCAT('Comisión ', @Id, ' creada correctamente.') 
				EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
				SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Comisión creada.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar comisión ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear la comisión.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DE LA COMISIÓN
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[Comisiones]
			SET [IdPromotor] = ISNULL(@IdPromotor, [IdPromotor])
			  ,[Comision] = ISNULL(@Comision, [Comision])
			  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
			  ,[IdUsuarioModifica] = @IdUsuarioActual
			  ,[FechaModificacion] = GETDATE()
			WHERE Id = @Id

			SET @log = CONCAT('Comisión ', @Id, ' actualizada correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual

			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Comisión actualizada.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar comisión ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar la comisión.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	
	-------------------------------------------------------------------------------------------
	--CONSULTAR LA COMISIÓN
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Comision].[Id]
		  ,[Comision].[IdPromotor]
		  ,[Promotores].[Nombre] AS Promotor
		  ,[Comision].[Comision]
		  ,[Comision].[IdEstatus]
		  ,[Estatus].[Nombre] AS Estatus
		  ,[Comision].[IdUsuarioCrea]
		  ,[Comision].[FechaCreacion]
		  ,[Comision].[IdUsuarioModifica]
		  ,[Comision].[FechaModificacion]
		FROM [datos].[Comisiones] AS [Comision]
		INNER JOIN [datos].[Promotores] AS [Promotores] ON [Comision].[IdPromotor] = [Promotores].[Id]
		INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Comision].[IdEstatus] = [Estatus].[IdEstatus]
		WHERE [Comision].[Id] = ISNULL(@Id, [Comision].[Id])
		AND [Comision].[IdEstatus] != 2
		AND [Comision].[IdPromotor] = ISNULL(@IdPromotor, [Comision].[IdPromotor])
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPDocumentos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 08/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS DOCUMENTOS
-- =============================================
CREATE PROCEDURE [datos].[SPDocumentos]
	@Opcion INT,
	@Id INT,
	@IdTipoDocumento INT,
	@IdActor INT,
	@IdTipoActor INT,
	@URLDocumento VARCHAR(MAX),
	@IdEstatus INT,
	@IdUsuarioActual INT

AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL DOCUMENTO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[Documentos])
			INSERT INTO [datos].[Documentos]
				   ([Id]
				   ,[IdTipoDocumento]
				   ,[IdActor]
				   ,[IdTipoActor]
				   ,[URLDocumento]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@IdTipoDocumento
				   ,@IdActor
				   ,@IdTipoActor
				   ,@URLDocumento
				   ,1
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Documento ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Documento creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar documento ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el documento.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL DOCUMENTO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[Documentos]
			   SET [IdTipoDocumento] = ISNULL(@IdTipoDocumento, [IdTipoDocumento])
				  ,[IdActor] = ISNULL(@IdActor, [IdActor])
				  ,[IdTipoActor] = ISNULL(@IdTipoActor, [IdTipoActor])
				  ,[URLDocumento] = ISNULL(@URLDocumento, [URLDocumento])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Documento ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Documento actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar documento ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar documento.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR DOCUMENTOS
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		IF (@IdTipoActor = 2)
		BEGIN
			SELECT [Documentos].[Id]
				  ,[Documentos].[IdTipoDocumento]
				  ,[TiposDocumentos].[Nombre] AS TipoDocumento
				  ,[Documentos].[IdActor]
				  ,[Promotores].[Nombre] AS Promotor
				  ,[Documentos].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS TipoActor
				  ,[Documentos].[URLDocumento]
				  ,[Documentos].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[Documentos].[IdUsuarioCrea]
				  ,[Documentos].[FechaCreacion]
				  ,[Documentos].[IdUsuarioModifica]
				  ,[Documentos].[FechaModificacion]
			  FROM [datos].[Documentos] AS [Documentos]
			  INNER JOIN [catalogos].[TiposDocumentos] AS [TiposDocumentos] ON [TiposDocumentos].[Id] = [Documentos].[IdTipoDocumento]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [Documentos].[IdTipoActor] 
			  INNER JOIN [datos].[Promotores] AS [Promotores] ON [Promotores].[Id] = [Documentos].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Documentos].[IdEstatus]
			  WHERE [Documentos].[IdActor] = ISNULL(@IdActor, [Documentos].[IdActor])
			  AND [Documentos].[IdTipoActor] = ISNULL(@IdTipoActor, [Documentos].[IdTipoActor])
			  AND [Documentos].[IdTipoDocumento] = ISNULL(@IdTipoDocumento, [Documentos].[IdTipoDocumento])
			  AND [Documentos].[IdEstatus] != 2
		END
		ELSE IF (@IdTipoActor = 3)
		BEGIN
			SELECT [Documentos].[Id]
				  ,[Documentos].[IdTipoDocumento]
				  ,[TiposDocumentos].[Nombre] AS TipoDocumento
				  ,[Documentos].[IdActor]
				  ,[Clientes].[Nombre] AS Cliente
				  ,[Documentos].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS TipoActor
				  ,[Documentos].[URLDocumento]
				  ,[Documentos].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[Documentos].[IdUsuarioCrea]
				  ,[Documentos].[FechaCreacion]
				  ,[Documentos].[IdUsuarioModifica]
				  ,[Documentos].[FechaModificacion]
			  FROM [datos].[Documentos] AS [Documentos]
			  INNER JOIN [catalogos].[TiposDocumentos] AS [TiposDocumentos] ON [TiposDocumentos].[Id] = [Documentos].[IdTipoDocumento]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [Documentos].[IdTipoActor] 
			  INNER JOIN [datos].[Clientes] AS [Clientes] ON [Clientes].[Id] = [Documentos].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Documentos].[IdEstatus]
			  WHERE [Documentos].[IdActor] = ISNULL(@IdActor, [Documentos].[IdActor])
			  AND [Documentos].[IdTipoActor] = ISNULL(@IdTipoActor, [Documentos].[IdTipoActor])
			  AND [Documentos].[IdTipoDocumento] = ISNULL(@IdTipoDocumento, [Documentos].[IdTipoDocumento])
			  AND [Documentos].[IdEstatus] != 2
		END
		ELSE IF (@IdTipoActor = 4)
		BEGIN
			SELECT [Documentos].[Id]
				  ,[Documentos].[IdTipoDocumento]
				  ,[TiposDocumentos].[Nombre] AS TipoDocumento
				  ,[Documentos].[IdActor]
				  ,[Grupos].[Nombre] AS Grupo
				  ,[Documentos].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS TipoActor
				  ,[Documentos].[URLDocumento]
				  ,[Documentos].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[Documentos].[IdUsuarioCrea]
				  ,[Documentos].[FechaCreacion]
				  ,[Documentos].[IdUsuarioModifica]
				  ,[Documentos].[FechaModificacion]
			  FROM [datos].[Documentos] AS [Documentos]
			  INNER JOIN [catalogos].[TiposDocumentos] AS [TiposDocumentos] ON [TiposDocumentos].[Id] = [Documentos].[IdTipoDocumento]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [Documentos].[IdTipoActor] 
			  INNER JOIN [datos].[Grupos] AS [Grupos] ON [Grupos].[Id] = [Documentos].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Documentos].[IdEstatus]
			  WHERE [Documentos].[IdActor] = ISNULL(@IdActor, [Documentos].[IdActor])
			  AND [Documentos].[IdTipoActor] = ISNULL(@IdTipoActor, [Documentos].[IdTipoActor])
			  AND [Documentos].[IdTipoDocumento] = ISNULL(@IdTipoDocumento, [Documentos].[IdTipoDocumento])
			  AND [Documentos].[IdEstatus] != 2
		END
		ELSE IF (@IdTipoActor = 5)
		BEGIN
			SELECT [Documentos].[Id]
				  ,[Documentos].[IdTipoDocumento]
				  ,[TiposDocumentos].[Nombre] AS TipoDocumento
				  ,[Documentos].[IdActor]
				  ,[ReferenciasPromotores].[Nombre] AS Referencia
				  ,[Documentos].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS TipoActor
				  ,[Documentos].[URLDocumento]
				  ,[Documentos].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[Documentos].[IdUsuarioCrea]
				  ,[Documentos].[FechaCreacion]
				  ,[Documentos].[IdUsuarioModifica]
				  ,[Documentos].[FechaModificacion]
			  FROM [datos].[Documentos] AS [Documentos]
			  INNER JOIN [catalogos].[TiposDocumentos] AS [TiposDocumentos] ON [TiposDocumentos].[Id] = [Documentos].[IdTipoDocumento]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [Documentos].[IdTipoActor] 
			  INNER JOIN [datos].[ReferenciasPromotores] AS [ReferenciasPromotores] ON [ReferenciasPromotores].[Id] = [Documentos].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Documentos].[IdEstatus]
			  WHERE [Documentos].[IdActor] = ISNULL(@IdActor, [Documentos].[IdActor])
			  AND [Documentos].[IdTipoActor] = ISNULL(@IdTipoActor, [Documentos].[IdTipoActor])
			  AND [Documentos].[IdTipoDocumento] = ISNULL(@IdTipoDocumento, [Documentos].[IdTipoDocumento])
			  AND [Documentos].[IdEstatus] != 2
		END
		ELSE IF (@IdTipoActor = 7)
		BEGIN
			SELECT [Documentos].[Id]
				  ,[Documentos].[IdTipoDocumento]
				  ,[TiposDocumentos].[Nombre] AS TipoDocumento
				  ,[Documentos].[IdActor]
				  ,[ReferenciasPrestamos].[Nombre] AS Referencia
				  ,[Documentos].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS TipoActor
				  ,[Documentos].[URLDocumento]
				  ,[Documentos].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[Documentos].[IdUsuarioCrea]
				  ,[Documentos].[FechaCreacion]
				  ,[Documentos].[IdUsuarioModifica]
				  ,[Documentos].[FechaModificacion]
			  FROM [datos].[Documentos] AS [Documentos]
			  INNER JOIN [catalogos].[TiposDocumentos] AS [TiposDocumentos] ON [TiposDocumentos].[Id] = [Documentos].[IdTipoDocumento]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [Documentos].[IdTipoActor] 
			  INNER JOIN [datos].[ReferenciasPrestamos] AS [ReferenciasPrestamos] ON [ReferenciasPrestamos].[Id] = [Documentos].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Documentos].[IdEstatus]
			  WHERE [Documentos].[IdActor] = ISNULL(@IdActor, [Documentos].[IdActor])
			  AND [Documentos].[IdTipoActor] = ISNULL(@IdTipoActor, [Documentos].[IdTipoActor])
			  AND [Documentos].[IdTipoDocumento] = ISNULL(@IdTipoDocumento, [Documentos].[IdTipoDocumento])
			  AND [Documentos].[IdEstatus] != 2
		END
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPGrupos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS GRUPOS
-- =============================================
CREATE PROCEDURE [datos].[SPGrupos]
	@Opcion INT,
	@Id INT,
	@Nombre VARCHAR(100),
	@IdClienteResponsable INT,
	@IdPromotor INT,
	@IdEstatus INT,
	@IdUsuarioActual INT

AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL GRUPO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[Grupos])
			INSERT INTO [datos].[Grupos]
				   ([Id]
				   ,[Nombre]
				   ,[IdClienteResponsable]
				   ,[IdPromotor]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@Nombre
				   ,@IdClienteResponsable
				   ,@IdPromotor
				   ,3
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Grupo ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Grupo creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar grupo ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el grupo.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL GRUPO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[Grupos]
			   SET [Nombre] = ISNULL(@Nombre, [Nombre])
				  ,[IdClienteResponsable] =  ISNULL(@IdClienteResponsable, [IdClienteResponsable])
				  ,[IdPromotor] =  ISNULL(@IdPromotor, [IdPromotor])
				  ,[IdEstatus] =  ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Grupo ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Grupo actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar grupo ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar grupo.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR GRUPO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Grupos].[Id]
			  ,[Grupos].[Nombre]
			  ,[Grupos].[IdClienteResponsable]
			  ,[Clientes].[Nombre] AS ClienteResponsable
			  ,[Grupos].[IdPromotor]
			  ,[Promotores].[Nombre] AS [Promotor]
			  ,[Grupos].[IdEstatus]
			  ,[Estatus].[Nombre] AS Estatus
			  ,[Grupos].[IdUsuarioCrea]
			  ,[Grupos].[FechaCreacion]
			  ,[Grupos].[IdUsuarioModifica]
			  ,[Grupos].[FechaModificacion]
		  FROM [datos].[Grupos] AS [Grupos]
		  INNER JOIN [datos].[Clientes] AS [Clientes] ON [Clientes].[Id] = [Grupos].[IdClienteResponsable]
		  INNER JOIN [datos].[Promotores] AS [Promotores] ON [Promotores].[Id] = [Grupos].[IdPromotor]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Grupos].[IdEstatus]
		  WHERE [Grupos].[Id] = ISNULL(@Id, [Grupos].[Id])
		  AND [Grupos].[IdEstatus] != 2
		  AND [Grupos].[IdPromotor] = ISNULL(@IdPromotor, [Grupos].[IdPromotor])
		  AND [Grupos].[Nombre] LIKE '%' + ISNULL(@Nombre, [Grupos].[Nombre]) + '%'
		  AND [Grupos].[IdClienteResponsable] = ISNULL(@IdClienteResponsable, [Grupos].[IdClienteResponsable])
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR GRUPO AUTORIZADOS
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 4)
	BEGIN
		SELECT [Grupos].[Id]
			  ,[Grupos].[Nombre]
			  ,[Grupos].[IdClienteResponsable]
			  ,[Clientes].[Nombre] AS ClienteResponsable
			  ,[Grupos].[IdPromotor]
			  ,[Promotores].[Nombre] AS [Promotor]
			  ,[Grupos].[IdEstatus]
			  ,[Estatus].[Nombre] AS Estatus
			  ,[Grupos].[IdUsuarioCrea]
			  ,[Grupos].[FechaCreacion]
			  ,[Grupos].[IdUsuarioModifica]
			  ,[Grupos].[FechaModificacion]
		  FROM [datos].[Grupos] AS [Grupos]
		  INNER JOIN [datos].[Clientes] AS [Clientes] ON [Clientes].[Id] = [Grupos].[IdClienteResponsable]
		  INNER JOIN [datos].[Promotores] AS [Promotores] ON [Promotores].[Id] = [Grupos].[IdPromotor]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Grupos].[IdEstatus]
		  WHERE [Grupos].[Id] = ISNULL(@Id, [Grupos].[Id])
		  AND [Grupos].[IdEstatus] = 4
		  AND [Grupos].[IdPromotor] = ISNULL(@IdPromotor, [Grupos].[IdPromotor])
		  AND [Grupos].[Nombre] LIKE '%' + ISNULL(@Nombre, [Grupos].[Nombre]) + '%'
		  AND [Grupos].[IdClienteResponsable] = ISNULL(@IdClienteResponsable, [Grupos].[IdClienteResponsable])
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPIntegrantesGrupos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS INTEGRANTES DEL GRUPO
-- =============================================
CREATE PROCEDURE [datos].[SPIntegrantesGrupos] 
	@Opcion INT,
	@Id INT,
	@IdGrupo INT,
	@IdCliente INT,
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL INTEGRANTE
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[IntegrantesGrupos])
			INSERT INTO [datos].[IntegrantesGrupos]
				   ([Id]
				   ,[IdGrupo]
				   ,[IdCliente]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@IdGrupo
				   ,@IdCliente
				   ,1
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Integrante ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Integrante creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar integrante ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el integrante.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL INTEGRANTE
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[IntegrantesGrupos]
			   SET [IdGrupo] = ISNULL(@IdGrupo, [IdGrupo])
				  ,[IdCliente] = ISNULL(@IdCliente, [IdCliente])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Integrante ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Integrante actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar integrante ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar integrante.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR INTEGRANTES
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Integrantes].[Id]
			  ,[Integrantes].[IdGrupo]
			  ,[Grupos].[Nombre] AS [Grupo]
			  ,[Integrantes].[IdCliente]
			  ,[Clientes].[Nombre] AS [Cliente]
			  ,[Integrantes].[IdEstatus]
			  ,[Estatus].[Nombre] AS [Estatus]
			  ,[Integrantes].[IdUsuarioCrea]
			  ,[Integrantes].[FechaCreacion]
			  ,[Integrantes].[IdUsuarioModifica]
			  ,[Integrantes].[FechaModificacion]
		  FROM [datos].[IntegrantesGrupos] AS [Integrantes]
		  INNER JOIN [datos].[Grupos] AS [Grupos] ON [Grupos].[Id] = [Integrantes].[IdGrupo]
		  INNER JOIN [datos].[Clientes] AS [Clientes] ON [Clientes].[Id] = [Integrantes].[IdCliente]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Integrantes].[IdEstatus]
		  WHERE [Integrantes].[IdGrupo] = ISNULL(@IdGrupo, [Integrantes].[IdGrupo])
		  AND [Integrantes].[IdEstatus] != 2
		  AND [Integrantes].[IdCliente] = ISNULL(@IdCliente, [Integrantes].[IdCliente])
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPPagos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS PAGOS
-- =============================================
CREATE PROCEDURE [datos].[SPPagos]
	@Opcion INT,
	@Id INT,
	@IdPrestamo INT,
	@IdCliente INT,
	@IdTipoPrestamo INT,
	@Monto MONEY,
	@Plazo VARCHAR(50),
	@TipoPago VARCHAR(50),
	@FechaProgramada DATETIME,
	@FechaPago DATETIME,
	@Morosidad MONEY,
	@Descripcion VARCHAR(MAX),
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL PAGO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[Pagos])
			INSERT INTO [datos].[Pagos]
				   ([Id]
				   ,[IdPrestamo]
				   ,[IdCliente]
				   ,[IdTipoPrestamo]
				   ,[Monto]
				   ,[Plazo]
				   ,[TipoPago]
				   ,[FechaProgramada]
				   ,[FechaPago]
				   ,[Morosidad]
				   ,[Descripcion]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCrea])
			 VALUES
				   (@Id
				   ,@IdPrestamo
				   ,@IdCliente
				   ,@IdTipoPrestamo
				   ,@Monto
				   ,@Plazo
				   ,@TipoPago
				   ,@FechaProgramada
				   ,@FechaPago
				   ,@Morosidad
				   ,@Descripcion
				   ,1
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Pago ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Pago creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar pago ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el pago.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL PAGO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[Pagos]
			   SET [IdPrestamo] = ISNULL(@IdPrestamo, [IdPrestamo])
				  ,[IdCliente] = ISNULL(@IdCliente, [IdCliente])
				  ,[IdTipoPrestamo] = ISNULL(@IdTipoPrestamo, [IdTipoPrestamo])
				  ,[Monto] = ISNULL(@Monto, [Monto])
				  ,[Plazo] = ISNULL(@Plazo, [Plazo])
				  ,[TipoPago] = ISNULL(@TipoPago, [TipoPago])
				  ,[FechaProgramada] = ISNULL(@FechaProgramada, [FechaProgramada])
				  ,[FechaPago] = ISNULL(@FechaPago, [FechaPago])
				  ,[Morosidad] = ISNULL(@Morosidad, [Morosidad])
				  ,[Descripcion] = ISNULL(@Descripcion, [Descripcion])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModifica] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Pago ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Pago actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar pago ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar pago.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR PAGOS
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Pagos].[Id]
				,[Pagos].[IdPrestamo]
				,[Pagos].[IdCliente]
				,[Clientes].[Nombre] AS Cliente
				,[Pagos].[IdTipoPrestamo]
				,[TiposPrestamos].[Nombre] AS TipoPrestamo
				,[Pagos].[Monto]
				,[Pagos].[Plazo]
				,[Pagos].[TipoPago]
				,[Pagos].[FechaProgramada]
				,[Pagos].[FechaPago]
				,[Pagos].[Morosidad]
				,[Pagos].[Descripcion]
				,[Pagos].[IdEstatus]
				,[Estatus].[Nombre] AS Estatus
				,[Pagos].[IdUsuarioCrea]
				,[Pagos].[FechaCrea]
				,[Pagos].[IdUsuarioModifica]
				,[Pagos].[FechaModifica]
			FROM [datos].[Pagos] AS [Pagos]
			INNER JOIN [catalogos].[TiposPrestamos] AS [TiposPrestamos] ON [TiposPrestamos].[Id] = [Pagos].[IdTipoPrestamo]
			INNER JOIN [datos].[Clientes] AS [Clientes] ON [Clientes].[Id] = [Pagos].[IdCliente]
			INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Pagos].[IdEstatus]
			WHERE [Pagos].[Id] = ISNULL(@Id, [Pagos].[Id])
			AND [Pagos].[IdPrestamo] = ISNULL(@IdPrestamo, [Pagos].[IdPrestamo]) 
			AND [Pagos].[IdTipoPrestamo] = ISNULL(@IdTipoPrestamo, [Pagos].[IdTipoPrestamo])
			AND [Pagos].[IdCliente] = ISNULL(NULL, [Pagos].[IdCliente])
			AND [Pagos].[Plazo] LIKE '%' + ISNULL(NULL, [Pagos].[Plazo]) + '%'
			AND [Pagos].[IdEstatus] = ISNULL(@IdEstatus, [Pagos].[IdEstatus])
			AND [Pagos].[IdEstatus] != 2
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR PRÓXIMOS PAGOS
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 4)
	BEGIN
		SELECT [Pagos].[Id]
				,[Pagos].[IdPrestamo]
				,[Pagos].[IdCliente]
				,[Clientes].[Nombre] AS Cliente
				,[Pagos].[IdTipoPrestamo]
				,[TiposPrestamos].[Nombre] AS TipoPrestamo
				,[Pagos].[Monto]
				,[Pagos].[Plazo]
				,[Pagos].[TipoPago]
				,[Pagos].[FechaProgramada]
				,[Pagos].[FechaPago]
				,[Pagos].[Morosidad]
				,[Pagos].[Descripcion]
				,[Pagos].[IdEstatus]
				,[Estatus].[Nombre] AS Estatus
				,[Pagos].[IdUsuarioCrea]
				,[Pagos].[FechaCrea]
				,[Pagos].[IdUsuarioModifica]
				,[Pagos].[FechaModifica]
			FROM [datos].[Pagos] AS [Pagos]
			INNER JOIN [catalogos].[TiposPrestamos] AS [TiposPrestamos] ON [TiposPrestamos].[Id] = [Pagos].[IdTipoPrestamo]
			INNER JOIN [datos].[Clientes] AS [Clientes] ON [Clientes].[Id] = [Pagos].[IdCliente]
			INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Pagos].[IdEstatus]
			WHERE [Pagos].[Id] = ISNULL(@Id, [Pagos].[Id])
			AND [Pagos].[IdPrestamo] = ISNULL(@IdPrestamo, [Pagos].[IdPrestamo]) 
			AND [Pagos].[IdTipoPrestamo] = ISNULL(@IdTipoPrestamo, [Pagos].[IdTipoPrestamo])
			AND [Pagos].[IdCliente] = ISNULL(NULL, [Pagos].[IdCliente])
			AND [Pagos].[Plazo] LIKE '%' + ISNULL(NULL, [Pagos].[Plazo]) + '%'
			AND (([Pagos].[IdEstatus] = 7
				AND [Pagos].[FechaProgramada] > GETDATE())
				OR ([Pagos].IdEstatus = 7))
			ORDER BY [Pagos].[FechaProgramada] ASC
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPPrestamosGrupales]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS PRESTAMOS GRUPALES
-- =============================================
CREATE PROCEDURE [datos].[SPPrestamosGrupales]
	@Opcion INT,
	@Id INT,
	@IdGrupo INT,
	@Motivo VARCHAR(MAX),
	@CantidadSolicitada MONEY,
	@CantidadOtorgada MONEY,
	@Interes FLOAT,
	@Garantia VARCHAR(100),
	@Anticipo MONEY,
	@FechaEntrega DATETIME,
	@Observaciones VARCHAR(MAX),
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL PRESTAMO GRUPAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[PrestamosGrupales])
			INSERT INTO [datos].[PrestamosGrupales]
				   ([Id]
				   ,[IdGrupo]
				   ,[Motivo]
				   ,[CantidadSolicitada]
				   ,[Interes]
				   ,[Garantia]
				   ,[Anticipo]
				   ,[FechaEntrega]
				   ,[Observaciones]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@IdGrupo
				   ,@Motivo
				   ,@CantidadSolicitada
				   ,@Interes
				   ,@Garantia
				   ,@Anticipo
				   ,@FechaEntrega
				   ,@Observaciones
				   ,3
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Préstamo Grupal ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Préstamo Grupal creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar préstamo grupal ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el préstamo grupal.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL PRESTAMO GRUPAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[PrestamosGrupales]
			   SET [IdGrupo] = ISNULL(@IdGrupo, [IdGrupo])
				  ,[Motivo] = ISNULL(@Motivo, [Motivo])
				  ,[CantidadSolicitada] = ISNULL(@CantidadSolicitada, [CantidadSolicitada])
				  ,[CantidadOtorgada] = ISNULL(@CantidadOtorgada, [CantidadOtorgada])
				  ,[Interes] = ISNULL(@Interes, [Interes])
				  ,[Garantia] = ISNULL(@Garantia, [Garantia])
				  ,[Anticipo] = ISNULL(@Anticipo, [Anticipo])
				  ,[FechaEntrega] = ISNULL(@FechaEntrega, [FechaEntrega])
				  ,[Observaciones] = ISNULL(@Observaciones, [Observaciones])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Préstamo Grupal ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Préstamo Grupal actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar préstamo grupal ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar préstamo grupal.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR PRESTAMO GRUPAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [PrestamosGrupales].[Id]
			  ,[PrestamosGrupales].[IdGrupo]
			  ,[Grupos].[Nombre] AS [Grupo]
			  ,[PrestamosGrupales].[Motivo]
			  ,[PrestamosGrupales].[CantidadSolicitada]
			  ,[PrestamosGrupales].[CantidadOtorgada]
			  ,[PrestamosGrupales].[Interes]
			  ,[PrestamosGrupales].[Garantia]
			  ,[PrestamosGrupales].[Anticipo]
			  ,[PrestamosGrupales].[FechaEntrega]
			  ,[PrestamosGrupales].[Observaciones]
			  ,[PrestamosGrupales].[IdEstatus]
			  ,[Estatus].[Nombre] AS [Estatus]
			  ,[PrestamosGrupales].[IdUsuarioCrea]
			  ,[PrestamosGrupales].[FechaCreacion]
			  ,[PrestamosGrupales].[IdUsuarioModifica]
			  ,[PrestamosGrupales].[FechaModificacion]
		  FROM [datos].[PrestamosGrupales] AS [PrestamosGrupales]
		  INNER JOIN [datos].[Grupos] AS [Grupos] ON [Grupos].[Id] = [PrestamosGrupales].[IdGrupo]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [PrestamosGrupales].[IdEstatus]
		  WHERE [PrestamosGrupales].[Id] = ISNULL(@Id, [PrestamosGrupales].[Id])
		  AND [PrestamosGrupales].[IdGrupo] = ISNULL(@IdGrupo, [PrestamosGrupales].[IdGrupo])
		  AND [PrestamosGrupales].[IdEstatus] != 2
		  ORDER BY [PrestamosGrupales].[FechaCreacion] DESC
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPPrestamosIndividuales]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS PRESTAMOS INDIVIDUALES
-- =============================================
CREATE PROCEDURE [datos].[SPPrestamosIndividuales]
	@Opcion INT,
	@Id INT,
	@IdCliente INT,
	@Motivo VARCHAR(MAX),
	@CantidadSolicitada MONEY,
	@CantidadOtorgada MONEY,
	@Interes FLOAT,
	@Garantia VARCHAR(100),
	@Anticipo MONEY,
	@FechaEntrega DATETIME,
	@Observaciones VARCHAR(MAX),
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL PRESTAMO INDIVIDUAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[PrestamosIndividuales])
			INSERT INTO [datos].[PrestamosIndividuales]
				   ([Id]
				   ,[IdCliente]
				   ,[Motivo]
				   ,[CantidadSolicitada]
				   ,[Interes]
				   ,[Garantia]
				   ,[Anticipo]
				   ,[FechaEntrega]
				   ,[Observaciones]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@IdCliente
				   ,@Motivo
				   ,@CantidadSolicitada
				   ,@Interes
				   ,@Garantia
				   ,@Anticipo
				   ,@FechaEntrega
				   ,@Observaciones
				   ,3
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Préstamo Individual ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Préstamo Individual creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar préstamo individual ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el préstamo individual.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL PRESTAMO GRUPAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[PrestamosIndividuales]
			   SET [IdCliente] = ISNULL(@IdCliente, [IdCliente])
				  ,[Motivo] = ISNULL(@Motivo, [Motivo])
				  ,[CantidadSolicitada] = ISNULL(@CantidadSolicitada, [CantidadSolicitada])
				  ,[CantidadOtorgada] = ISNULL(@CantidadOtorgada, [CantidadOtorgada])
				  ,[Interes] = ISNULL(@Interes, [Interes])
				  ,[Garantia] = ISNULL(@Garantia, [Garantia])
				  ,[Anticipo] = ISNULL(@Anticipo, [Anticipo])
				  ,[FechaEntrega] = ISNULL(@FechaEntrega, [FechaEntrega])
				  ,[Observaciones] = ISNULL(@Observaciones, [Observaciones])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Préstamo Individual ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Préstamo Individual actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar préstamo individual ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar préstamo individual.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR PRESTAMO GRUPAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [PrestamosIndividuales].[Id]
			  ,[PrestamosIndividuales].[IdCliente]
			  ,[Clientes].[Nombre] AS [Cliente]
			  ,[PrestamosIndividuales].[Motivo]
			  ,[PrestamosIndividuales].[CantidadSolicitada]
			  ,[PrestamosIndividuales].[CantidadOtorgada]
			  ,[PrestamosIndividuales].[Interes]
			  ,[PrestamosIndividuales].[Garantia]
			  ,[PrestamosIndividuales].[Anticipo]
			  ,[PrestamosIndividuales].[FechaEntrega]
			  ,[PrestamosIndividuales].[Observaciones]
			  ,[PrestamosIndividuales].[IdEstatus]
			  ,[Estatus].[Nombre] AS [Estatus]
			  ,[PrestamosIndividuales].[IdUsuarioCrea]
			  ,[PrestamosIndividuales].[FechaCreacion]
			  ,[PrestamosIndividuales].[IdUsuarioModifica]
			  ,[PrestamosIndividuales].[FechaModificacion]
		  FROM [datos].[PrestamosIndividuales] AS [PrestamosIndividuales]
		  INNER JOIN [datos].[Clientes] AS [Clientes] ON [Clientes].[Id] = [PrestamosIndividuales].[IdCliente]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [PrestamosIndividuales].[IdEstatus]
		  WHERE [PrestamosIndividuales].[Id] = ISNULL(@Id, [PrestamosIndividuales].[Id])
		  AND [PrestamosIndividuales].[IdCliente] = ISNULL(@IdCliente, [PrestamosIndividuales].[IdCliente])
		  AND [PrestamosIndividuales].[IdEstatus] != 2
		  ORDER BY [PrestamosIndividuales].[FechaCreacion] DESC
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPPromotores]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS PROMOTORES
-- =============================================
CREATE PROCEDURE [datos].[SPPromotores]
	@Opcion INT,
	@Id INT,
	@Nombre VARCHAR(100),
	@Direccion VARCHAR(MAX),
	@TelefonoCasa VARCHAR(50),
	@TelefonoCelular VARCHAR(50),
	@CorreoElectronico VARCHAR(50),
	@FechaNacimiento DATETIME,
	@RFC VARCHAR(50),
	@CURP VARCHAR(50),
	@ClaveElector VARCHAR(50),
	@URLFoto VARCHAR(MAX),
	@IdEstatus INT,
	@IdUsuarioActual INT

AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL PROMOTOR
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[Promotores])
			INSERT INTO [datos].[Promotores]
				   ([Id]
				   ,[Nombre]
				   ,[Direccion]
				   ,[TelefonoCasa]
				   ,[TelefonoCelular]
				   ,[CorreoElectronico]
				   ,[FechaNacimiento]
				   ,[RFC]
				   ,[CURP]
				   ,[ClaveElector]
				   ,[URLFoto]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@Nombre
				   ,@Direccion
				   ,@TelefonoCasa
				   ,@TelefonoCelular
				   ,@CorreoElectronico
				   ,@FechaNacimiento
				   ,@RFC
				   ,@CURP
				   ,@ClaveElector
				   ,@URLFoto
				   ,1
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Promotor ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Promotor creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar promotor ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el promotor.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL PROMOTOR
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[Promotores]
			   SET [Nombre] = ISNULL(@Nombre, [Nombre])
				  ,[Direccion] = ISNULL(@Direccion, [Direccion])
				  ,[TelefonoCasa] = ISNULL(@TelefonoCasa, [TelefonoCasa])
				  ,[TelefonoCelular] = ISNULL(@TelefonoCelular, [TelefonoCelular])
				  ,[CorreoElectronico] = ISNULL(@CorreoElectronico, [CorreoElectronico])
				  ,[FechaNacimiento] = ISNULL(@FechaNacimiento, [FechaNacimiento])
				  ,[RFC] = ISNULL(@RFC, [RFC])
				  ,[CURP] = ISNULL(@CURP, [CURP])
				  ,[ClaveElector] = ISNULL(@ClaveElector, [ClaveElector])
				  ,[URLFoto] = ISNULL(@URLFoto, [URLFoto])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Promotor ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Promotor actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar promotor ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar promotor.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR PROMOTOR
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Promotores].[Id]
			  ,[Promotores].[Nombre]
			  ,[Promotores].[Direccion]
			  ,[Promotores].[TelefonoCasa]
			  ,[Promotores].[TelefonoCelular]
			  ,[Promotores].[CorreoElectronico]
			  ,[Promotores].[FechaNacimiento]
			  ,[Promotores].[RFC]
			  ,[Promotores].[CURP]
			  ,[Promotores].[ClaveElector]
			  ,[Promotores].[URLFoto]
			  ,[Promotores].[IdEstatus]
			  ,[Estatus].[Nombre] AS [Estatus]
			  ,[Promotores].[IdUsuarioCrea]
			  ,[Promotores].[FechaCreacion]
			  ,[Promotores].[IdUsuarioModifica]
			  ,[Promotores].[FechaModificacion]
		  FROM [datos].[Promotores] AS [Promotores]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Promotores].[IdEstatus]
		  WHERE [Promotores].[Id] = ISNULL(@Id, [Promotores].[Id])
		  AND [Promotores].[IdEstatus] != 2
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPRedesSociales]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LAS REDES SOCIALES
-- =============================================
CREATE PROCEDURE [datos].[SPRedesSociales]
	@Opcion INT,
	@Id INT,
	@IdTipoRedSocial INT,
	@IdActor INT,
	@IdTipoActor INT,
	@URL VARCHAR(100),
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DE LA RED SOCIAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[RedesSociales])
			INSERT INTO [datos].[RedesSociales]
				   ([Id]
				   ,[IdTipoRedSocial]
				   ,[IdActor]
				   ,[IdTipoActor]
				   ,[URL]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@IdTipoRedSocial
				   ,@IdActor
				   ,@IdTipoActor
				   ,@URL
				   ,1
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Red Social ', @Id, ' creada correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Red Social creada.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar red social ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear la red social.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DE LA RED SOCIAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[RedesSociales]
			   SET [IdTipoRedSocial] = ISNULL(@IdTipoRedSocial, [IdTipoRedSocial])
				  ,[IdActor] = ISNULL(@IdActor, [IdActor])
				  ,[IdTipoActor] = ISNULL(@IdTipoActor, [IdTipoActor])
				  ,[URL] = ISNULL(@URL, [URL])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Red Social ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Red Social actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar red social ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar red social.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR RED SOCIAL
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		IF (@IdTipoActor = 2)
		BEGIN
			SELECT [RedesSociales].[Id]
				  ,[RedesSociales].[IdTipoRedSocial]
				  ,[TiposRedesSociales].[Nombre] AS [TipoRedSocial]
				  ,[RedesSociales].[IdActor]
				  ,[Promotores].[Nombre] AS Promotor
				  ,[RedesSociales].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS [TipoActor]
				  ,[RedesSociales].[URL]
				  ,[RedesSociales].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[RedesSociales].[IdUsuarioCrea]
				  ,[RedesSociales].[FechaCreacion]
				  ,[RedesSociales].[IdUsuarioModifica]
				  ,[RedesSociales].[FechaModificacion]
			  FROM [datos].[RedesSociales] AS [RedesSociales]
			  INNER JOIN [catalogos].[TiposRedesSociales] AS [TiposRedesSociales] ON [TiposRedesSociales].[Id] = [RedesSociales].[IdTipoRedSocial]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [RedesSociales].[IdTipoActor]
			  INNER JOIN [datos].[Promotores] AS [Promotores] ON [Promotores].[Id] = [RedesSociales].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [RedesSociales].[IdEstatus]
			  WHERE [RedesSociales].[IdActor] = ISNULL(@IdActor, [RedesSociales].[IdActor])
			  AND [RedesSociales].[IdTipoActor] = ISNULL(@IdTipoActor, [RedesSociales].[IdTipoActor])
			  AND [RedesSociales].[IdTipoRedSocial] = ISNULL(@IdTipoRedSocial, [RedesSociales].[IdTipoRedSocial])
			  AND [RedesSociales].[Id] = ISNULL(@Id, [RedesSociales].[Id])
			  AND [RedesSociales].[IdEstatus] != 2
		END
		ELSE IF (@IdTipoActor = 3)
		BEGIN
			SELECT [RedesSociales].[Id]
				  ,[RedesSociales].[IdTipoRedSocial]
				  ,[TiposRedesSociales].[Nombre] AS [TipoRedSocial]
				  ,[RedesSociales].[IdActor]
				  ,[Clientes].[Nombre] AS Cliente
				  ,[RedesSociales].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS [TipoActor]
				  ,[RedesSociales].[URL]
				  ,[RedesSociales].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[RedesSociales].[IdUsuarioCrea]
				  ,[RedesSociales].[FechaCreacion]
				  ,[RedesSociales].[IdUsuarioModifica]
				  ,[RedesSociales].[FechaModificacion]
			  FROM [datos].[RedesSociales] AS [RedesSociales]
			  INNER JOIN [catalogos].[TiposRedesSociales] AS [TiposRedesSociales] ON [TiposRedesSociales].[Id] = [RedesSociales].[IdTipoRedSocial]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [RedesSociales].[IdTipoActor]
			  INNER JOIN [datos].[Clientes] AS [Clientes] ON [Clientes].[Id] = [RedesSociales].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [RedesSociales].[IdEstatus]
			  WHERE [RedesSociales].[IdActor] = ISNULL(@IdActor, [RedesSociales].[IdActor])
			  AND [RedesSociales].[IdTipoActor] = ISNULL(@IdTipoActor, [RedesSociales].[IdTipoActor])
			  AND [RedesSociales].[IdTipoRedSocial] = ISNULL(@IdTipoRedSocial, [RedesSociales].[IdTipoRedSocial])
			  AND [RedesSociales].[Id] = ISNULL(@Id, [RedesSociales].[Id])
			  AND [RedesSociales].[IdEstatus] != 2
		END
		ELSE IF (@IdTipoActor = 5)
		BEGIN
			SELECT [RedesSociales].[Id]
				  ,[RedesSociales].[IdTipoRedSocial]
				  ,[TiposRedesSociales].[Nombre] AS [TipoRedSocial]
				  ,[RedesSociales].[IdActor]
				  ,[Referencias].[Nombre] AS Referencia
				  ,[RedesSociales].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS [TipoActor]
				  ,[RedesSociales].[URL]
				  ,[RedesSociales].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[RedesSociales].[IdUsuarioCrea]
				  ,[RedesSociales].[FechaCreacion]
				  ,[RedesSociales].[IdUsuarioModifica]
				  ,[RedesSociales].[FechaModificacion]
			  FROM [datos].[RedesSociales] AS [RedesSociales]
			  INNER JOIN [catalogos].[TiposRedesSociales] AS [TiposRedesSociales] ON [TiposRedesSociales].[Id] = [RedesSociales].[IdTipoRedSocial]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [RedesSociales].[IdTipoActor]
			  INNER JOIN [datos].[ReferenciasPromotores] AS [Referencias] ON [Referencias].[Id] = [RedesSociales].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [RedesSociales].[IdEstatus]
			  WHERE [RedesSociales].[IdActor] = ISNULL(@IdActor, [RedesSociales].[IdActor])
			  AND [RedesSociales].[IdTipoActor] = ISNULL(@IdTipoActor, [RedesSociales].[IdTipoActor])
			  AND [RedesSociales].[IdTipoRedSocial] = ISNULL(@IdTipoRedSocial, [RedesSociales].[IdTipoRedSocial])
			  AND [RedesSociales].[Id] = ISNULL(@Id, [RedesSociales].[Id])
			  AND [RedesSociales].[IdEstatus] != 2
		END
		ELSE IF (@IdTipoActor = 7)
		BEGIN
			SELECT [RedesSociales].[Id]
				  ,[RedesSociales].[IdTipoRedSocial]
				  ,[TiposRedesSociales].[Nombre] AS [TipoRedSocial]
				  ,[RedesSociales].[IdActor]
				  ,[Referencias].[Nombre] AS Referencia
				  ,[RedesSociales].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS [TipoActor]
				  ,[RedesSociales].[URL]
				  ,[RedesSociales].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[RedesSociales].[IdUsuarioCrea]
				  ,[RedesSociales].[FechaCreacion]
				  ,[RedesSociales].[IdUsuarioModifica]
				  ,[RedesSociales].[FechaModificacion]
			  FROM [datos].[RedesSociales] AS [RedesSociales]
			  INNER JOIN [catalogos].[TiposRedesSociales] AS [TiposRedesSociales] ON [TiposRedesSociales].[Id] = [RedesSociales].[IdTipoRedSocial]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [RedesSociales].[IdTipoActor]
			  INNER JOIN [datos].[ReferenciasPrestamos] AS [Referencias] ON [Referencias].[Id] = [RedesSociales].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [RedesSociales].[IdEstatus]
			  WHERE [RedesSociales].[IdActor] = ISNULL(@IdActor, [RedesSociales].[IdActor])
			  AND [RedesSociales].[IdTipoActor] = ISNULL(@IdTipoActor, [RedesSociales].[IdTipoActor])
			  AND [RedesSociales].[IdTipoRedSocial] = ISNULL(@IdTipoRedSocial, [RedesSociales].[IdTipoRedSocial])
			  AND [RedesSociales].[Id] = ISNULL(@Id, [RedesSociales].[Id])
			  AND [RedesSociales].[IdEstatus] != 2
		END
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR RED SOCIAL DE LA REFERENCIA DEL PROMOTOR
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 4)
	BEGIN
		SELECT [RedesSociales].[Id]
				  ,[RedesSociales].[IdTipoRedSocial]
				  ,[TiposRedesSociales].[Nombre] AS [TipoRedSocial]
				  ,[RedesSociales].[IdActor]
				  ,[Referencias].[Nombre] AS Referencia
				  ,[RedesSociales].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS [TipoActor]
				  ,[RedesSociales].[URL]
				  ,[RedesSociales].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[RedesSociales].[IdUsuarioCrea]
				  ,[RedesSociales].[FechaCreacion]
				  ,[RedesSociales].[IdUsuarioModifica]
				  ,[RedesSociales].[FechaModificacion]
			  FROM [datos].[RedesSociales] AS [RedesSociales]
			  INNER JOIN [catalogos].[TiposRedesSociales] AS [TiposRedesSociales] ON [TiposRedesSociales].[Id] = [RedesSociales].[IdTipoRedSocial]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [RedesSociales].[IdTipoActor]
			  INNER JOIN [datos].[ReferenciasPromotores] AS [Referencias] ON [Referencias].[Id] = [RedesSociales].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [RedesSociales].[IdEstatus]
			  WHERE [RedesSociales].[IdActor] = ISNULL(@IdActor, [RedesSociales].[IdActor])
			  AND [RedesSociales].[IdTipoActor] = ISNULL(@IdTipoActor, [RedesSociales].[IdTipoActor])
			  AND [RedesSociales].[IdTipoRedSocial] = ISNULL(@IdTipoRedSocial, [RedesSociales].[IdTipoRedSocial])
			  AND [RedesSociales].[Id] = ISNULL(@Id, [RedesSociales].[Id])
			  AND [RedesSociales].[IdEstatus] != 2
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR RED SOCIAL DE LA REFERENCIA DEL PRÉSTAMO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 5)
	BEGIN
		SELECT [RedesSociales].[Id]
				  ,[RedesSociales].[IdTipoRedSocial]
				  ,[TiposRedesSociales].[Nombre] AS [TipoRedSocial]
				  ,[RedesSociales].[IdActor]
				  ,[Referencias].[Nombre] AS Referencia
				  ,[RedesSociales].[IdTipoActor]
				  ,[TiposActores].[Nombre] AS [TipoActor]
				  ,[RedesSociales].[URL]
				  ,[RedesSociales].[IdEstatus]
				  ,[Estatus].[Nombre] AS [Estatus]
				  ,[RedesSociales].[IdUsuarioCrea]
				  ,[RedesSociales].[FechaCreacion]
				  ,[RedesSociales].[IdUsuarioModifica]
				  ,[RedesSociales].[FechaModificacion]
			  FROM [datos].[RedesSociales] AS [RedesSociales]
			  INNER JOIN [catalogos].[TiposRedesSociales] AS [TiposRedesSociales] ON [TiposRedesSociales].[Id] = [RedesSociales].[IdTipoRedSocial]
			  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [RedesSociales].[IdTipoActor]
			  INNER JOIN [datos].[ReferenciasPrestamos] AS [Referencias] ON [Referencias].[Id] = [RedesSociales].[IdActor]
			  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [RedesSociales].[IdEstatus]
			  WHERE [RedesSociales].[IdActor] = ISNULL(@IdActor, [RedesSociales].[IdActor])
			  AND [RedesSociales].[IdTipoActor] = ISNULL(@IdTipoActor, [RedesSociales].[IdTipoActor])
			  AND [RedesSociales].[IdTipoRedSocial] = ISNULL(@IdTipoRedSocial, [RedesSociales].[IdTipoRedSocial])
			  AND [RedesSociales].[Id] = ISNULL(@Id, [RedesSociales].[Id])
			  AND [RedesSociales].[IdEstatus] != 2
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPReferenciasPrestamos]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LAS REFERENCIAS
-- EXEC [datos].[SPReferenciasPrestamos] 3, null, 2, null, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 0
-- =============================================
CREATE PROCEDURE [datos].[SPReferenciasPrestamos]
	@Opcion INT,
	@Id INT,
	@IdPrestamo INT,
	@IdTipoPrestamo INT,
	@IdTipoReferencia INT,
	@Nombre VARCHAR(100),
	@RFC VARCHAR(50),
	@CURP VARCHAR(50),
	@FechaNacimiento DATETIME,
	@ClaveElector VARCHAR(50),
	@Direccion VARCHAR(MAX),
	@ReferenciaDireccion VARCHAR(MAX),
	@TelefonoCasa VARCHAR(50),
	@TelefonoCelular VARCHAR(50),
	@CorreoElectronico VARCHAR(50),
	@Parentesco VARCHAR(50),
	@URLFoto VARCHAR(MAX),
	@Empresa VARCHAR(50),
	@PuestoEmpresa VARCHAR(50),
	@DireccionEmpresa VARCHAR(100),
	@AntiguedadEmpresa VARCHAR(50),
	@TelefonoEmpresa VARCHAR(50),
	@NombreJefe VARCHAR(100),
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DE LA REFERENCIA
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[ReferenciasPrestamos])
			INSERT INTO [datos].[ReferenciasPrestamos]
				   ([Id]
				   ,[IdPrestamo]
				   ,[IdTipoPrestamo]
				   ,[IdTipoReferencia]
				   ,[Nombre]
				   ,[RFC]
				   ,[CURP]
				   ,[FechaNacimiento]
				   ,[ClaveElector]
				   ,[Direccion]
				   ,[ReferenciaDireccion]
				   ,[TelefonoCasa]
				   ,[TelefonoCelular]
				   ,[CorreoElectronico]
				   ,[Parentesco]
				   ,[URLFoto]
				   ,[Empresa]
				   ,[PuestoEmpresa]
				   ,[DireccionEmpresa]
				   ,[AntiguedadEmpresa]
				   ,[TelefonoEmpresa]
				   ,[NombreJefe]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@IdPrestamo
				   ,@IdTipoPrestamo
				   ,@IdTipoReferencia
				   ,@Nombre
				   ,@RFC
				   ,@CURP
				   ,@FechaNacimiento
				   ,@ClaveElector
				   ,@Direccion
				   ,@ReferenciaDireccion
				   ,@TelefonoCasa
				   ,@TelefonoCelular
				   ,@CorreoElectronico
				   ,@Parentesco
				   ,@URLFoto
				   ,@Empresa
				   ,@PuestoEmpresa
				   ,@DireccionEmpresa
				   ,@AntiguedadEmpresa
				   ,@TelefonoEmpresa
				   ,@NombreJefe
				   ,1
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Referencia ', @Id, ' creada correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Referencia creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar referencia ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear la referencia.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DE LA REFERENCIA
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[ReferenciasPrestamos]
			   SET [IdPrestamo] = ISNULL(@IdPrestamo, [IdPrestamo])
				  ,[IdTipoPrestamo] = ISNULL(@IdTipoPrestamo, [IdTipoPrestamo])
				  ,[IdTipoReferencia] = ISNULL(@IdTipoReferencia, [IdTipoReferencia])
				  ,[Nombre] = ISNULL(@Nombre, [Nombre])
				  ,[RFC] = ISNULL(@RFC, [RFC])
				  ,[CURP] = ISNULL(@CURP, [CURP])
				  ,[FechaNacimiento] = ISNULL(@FechaNacimiento, [FechaNacimiento])
				  ,[ClaveElector] = ISNULL(@ClaveElector, [ClaveElector])
				  ,[Direccion] = ISNULL(@Direccion, [Direccion])
				  ,[ReferenciaDireccion] = ISNULL(@ReferenciaDireccion, [ReferenciaDireccion])
				  ,[TelefonoCasa] = ISNULL(@TelefonoCasa, [TelefonoCasa])
				  ,[TelefonoCelular] = ISNULL(@TelefonoCelular, [TelefonoCelular])
				  ,[CorreoElectronico] = ISNULL(@CorreoElectronico, [CorreoElectronico])
				  ,[Parentesco] = ISNULL(@Parentesco, [Parentesco])
				  ,[URLFoto] = ISNULL(@URLFoto, [URLFoto])
				  ,[Empresa] = ISNULL(@Empresa, [Empresa])
				  ,[PuestoEmpresa] = ISNULL(@PuestoEmpresa, [PuestoEmpresa])
				  ,[DireccionEmpresa] = ISNULL(@DireccionEmpresa, [DireccionEmpresa])
				  ,[AntiguedadEmpresa] = ISNULL(@AntiguedadEmpresa, [AntiguedadEmpresa])
				  ,[TelefonoEmpresa] = ISNULL(@TelefonoEmpresa, [TelefonoEmpresa])
				  ,[NombreJefe] = ISNULL(@NombreJefe, [NombreJefe])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Referencia ', @Id, ' actualizada correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Referencia actualizada.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar referencia ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar referencia.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR REFERENCIA
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Referencias].[Id]
				,[Referencias].[IdPrestamo]
				,[Referencias].[IdTipoPrestamo]
				,[Referencias].[Nombre] AS [Referencia]
				,[TiposReferencias].[Nombre] AS [TipoActorReferencia]
				,[Referencias].[IdTipoReferencia]
				,[TiposReferencias].[Nombre] AS [TipoReferencia]
				,[Referencias].[Nombre]
				,[Referencias].[RFC]
				,[Referencias].[CURP]
				,[Referencias].[FechaNacimiento]
				,[Referencias].[ClaveElector]
				,[Referencias].[Direccion]
				,[Referencias].[ReferenciaDireccion]
				,[Referencias].[TelefonoCasa]
				,[Referencias].[TelefonoCelular]
				,[Referencias].[CorreoElectronico]
				,[Referencias].[Parentesco]
				,[Referencias].[URLFoto]
				,[Referencias].[Empresa]
				,[Referencias].[PuestoEmpresa]
				,[Referencias].[DireccionEmpresa]
				,[Referencias].[AntiguedadEmpresa]
				,[Referencias].[TelefonoEmpresa]
				,[Referencias].[NombreJefe]
				,[Referencias].[IdEstatus]
				,[Estatus].[Nombre] AS Estatus
				,[Referencias].[IdUsuarioCrea]
				,[Referencias].[FechaCreacion]
				,[Referencias].[IdUsuarioModifica]
				,[Referencias].[FechaModificacion]
			FROM [datos].[ReferenciasPrestamos] AS [Referencias]
			INNER JOIN [catalogos].[TiposReferencias] AS [TiposReferencias] ON [TiposReferencias].[Id] = [Referencias].[IdTipoReferencia]
			INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Referencias].[IdEstatus]
			WHERE [Referencias].[Nombre] LIKE '%' + ISNULL(@Nombre, [Referencias].[Nombre]) + '%'
			AND [Referencias].[IdPrestamo] = ISNULL(@IdPrestamo, [Referencias].[IdPrestamo])
			AND [Referencias].[IdTipoPrestamo] = ISNULL(@idTipoPrestamo, [Referencias].[IdTipoPrestamo])
			AND [Referencias].[IdTipoReferencia] = ISNULL(@IdTipoReferencia, [Referencias].[IdTipoReferencia])
			AND [Referencias].[Id] = ISNULL(@Id, [Referencias].[Id])
	END
END


GO
/****** Object:  StoredProcedure [datos].[SPReferenciasPromotores]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 09/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LAS REFERENCIAS
-- =============================================
CREATE PROCEDURE [datos].[SPReferenciasPromotores]
	@Opcion INT,
	@Id INT,
	@IdActor INT,
	@IdTipoReferencia INT,
	@Nombre VARCHAR(100),
	@RFC VARCHAR(50),
	@CURP VARCHAR(50),
	@FechaNacimiento DATETIME,
	@ClaveElector VARCHAR(50),
	@Direccion VARCHAR(MAX),
	@TelefonoCasa VARCHAR(50),
	@TelefonoCelular VARCHAR(50),
	@CorreoElectronico VARCHAR(50),
	@URLFoto VARCHAR(MAX),
	@IdEstatus INT,
	@IdUsuarioActual INT
AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DE LA REFERENCIA
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [datos].[ReferenciasPromotores])
			INSERT INTO [datos].[ReferenciasPromotores]
				   ([Id]
				   ,[IdActor]
				   ,[IdTipoReferencia]
				   ,[Nombre]
				   ,[RFC]
				   ,[CURP]
				   ,[FechaNacimiento]
				   ,[ClaveElector]
				   ,[Direccion]
				   ,[TelefonoCasa]
				   ,[TelefonoCelular]
				   ,[CorreoElectronico]
				   ,[URLFoto]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@IdActor
				   ,@IdTipoReferencia
				   ,@Nombre
				   ,@RFC
				   ,@CURP
				   ,@FechaNacimiento
				   ,@ClaveElector
				   ,@Direccion
				   ,@TelefonoCasa
				   ,@TelefonoCelular
				   ,@CorreoElectronico
				   ,@URLFoto
				   ,1
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Referencia ', @Id, ' creada correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Referencia creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar referencia ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear la referencia.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DE LA REFERENCIA
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [datos].[ReferenciasPromotores]
			   SET [IdActor] = ISNULL(@IdActor, [IdActor])
				  ,[IdTipoReferencia] = ISNULL(@IdTipoReferencia, [IdTipoReferencia])
				  ,[Nombre] = ISNULL(@Nombre, [Nombre])
				  ,[RFC] = ISNULL(@RFC, [RFC])
				  ,[CURP] = ISNULL(@CURP, [CURP])
				  ,[FechaNacimiento] = ISNULL(@FechaNacimiento, [FechaNacimiento])
				  ,[ClaveElector] = ISNULL(@ClaveElector, [ClaveElector])
				  ,[Direccion] = ISNULL(@Direccion, [Direccion])
				  ,[TelefonoCasa] = ISNULL(@TelefonoCasa, [TelefonoCasa])
				  ,[TelefonoCelular] = ISNULL(@TelefonoCelular, [TelefonoCelular])
				  ,[CorreoElectronico] = ISNULL(@CorreoElectronico, [CorreoElectronico])
				  ,[URLFoto] = ISNULL(@URLFoto, [URLFoto])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Referencia ', @Id, ' actualizada correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Referencia actualizada.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar referencia ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar referencia.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR REFERENCIA
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Referencias].[Id]
				,[Referencias].[IdActor]
				,[Promotores].[Nombre] AS [Promotor]
				,[Referencias].[IdTipoReferencia]
				,[TiposReferencias].[Nombre] AS [TipoReferencia]
				,[Referencias].[Nombre]
				,[Referencias].[RFC]
				,[Referencias].[CURP]
				,[Referencias].[FechaNacimiento]
				,[Referencias].[ClaveElector]
				,[Referencias].[Direccion]
				,[Referencias].[TelefonoCasa]
				,[Referencias].[TelefonoCelular]
				,[Referencias].[CorreoElectronico]
				,[Referencias].[URLFoto]
				,[Referencias].[IdEstatus]
				,[Estatus].[Nombre] AS Estatus
				,[Referencias].[IdUsuarioCrea]
				,[Referencias].[FechaCreacion]
				,[Referencias].[IdUsuarioModifica]
				,[Referencias].[FechaModificacion]
			FROM [datos].[ReferenciasPromotores] AS [Referencias]
			INNER JOIN [datos].[Promotores] AS [Promotores] ON [Promotores].[Id] = [Referencias].[IdActor]
			INNER JOIN [catalogos].[TiposReferencias] AS [TiposReferencias] ON [TiposReferencias].[Id] = [Referencias].[IdTipoReferencia]
			INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Referencias].[IdEstatus]
			WHERE [Referencias].[Nombre] LIKE '%' + ISNULL(@Nombre, [Referencias].[Nombre]) + '%'
			AND [Referencias].[IdActor] = ISNULL(@IdActor, [Referencias].[IdActor])
			AND [Referencias].[Id] = ISNULL(@Id, [Referencias].[Id])
	END
END


GO
/****** Object:  StoredProcedure [dbo].[usp_GetErrorInfo]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Create procedure to retrieve error information.  
CREATE PROCEDURE [dbo].[usp_GetErrorInfo]  
AS  
SELECT  
    ERROR_NUMBER() AS ErrorNumber  
    ,ERROR_SEVERITY() AS ErrorSeverity  
    ,ERROR_STATE() AS ErrorState  
    ,ERROR_PROCEDURE() AS ErrorProcedure  
    ,ERROR_LINE() AS ErrorLine  
    ,ERROR_MESSAGE() AS ErrorMessage;  


GO
/****** Object:  StoredProcedure [seguridad].[SPLog]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA
-- Create date: 31-07-2017
-- Description:	PROCEDIMIENTO ALMACENADO PARA INSERTA EN EL LOG
-- Example: EXEC seguridad.SPlog NULL, 'este es mi primer log'
-- =============================================
CREATE PROCEDURE [seguridad].[SPLog] 
	-- Add the parameters for the stored procedure here
	@Descripcion VARCHAR(MAX),
	@IdUsuarioCrea INT
AS
BEGIN
	DECLARE @Id INT
	SET @Id = (SELECT ISNULL(MAX([Id]), 0) + 1 FROM [seguridad].[Log])
	INSERT INTO [seguridad].[Log]
			   ([Id]
			   ,[Descripcion]
			   ,[IdUsuarioCrea]
			   ,[FechaCreacion])
		 VALUES
			   (@Id
			   ,@Descripcion
			   ,@IdUsuarioCrea
			   ,GETDATE())
END


GO
/****** Object:  StoredProcedure [seguridad].[SPUsuarios]    Script Date: 12/10/2017 10:33:55 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		FRED GÓMEZ LEYVA	
-- Create date: 10/08/2017
-- Description:	PROCEDIMIENTO ALMACENADO CORRESPONDIENTE A LOS USUARIOS
-- =============================================
CREATE PROCEDURE [seguridad].[SPUsuarios]
	@Opcion INT,
	@Id INT,
	@IdActor INT,
	@IdTipoActor INT,
	@Nombre VARCHAR(50),
	@Contrasena VARCHAR(50),
	@IdEstatus INT,
	@IdUsuarioActual INT

AS
BEGIN
	DECLARE @log VARCHAR(50)
	-------------------------------------------------------------------------------------------
	--CREACION DEL USUARIO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 1)
	BEGIN
		BEGIN TRY
			SET @Id = (SELECT ISNULL(MAX(Id), 0) + 1 FROM [seguridad].[Usuarios])
			INSERT INTO [seguridad].[Usuarios]
				   ([Id]
				   ,[IdActor]
				   ,[IdTipoActor]
				   ,[Nombre]
				   ,[Contrasena]
				   ,[IdEstatus]
				   ,[IdUsuarioCrea]
				   ,[FechaCreacion])
			 VALUES
				   (@Id
				   ,@IdActor
				   ,@IdTipoActor
				   ,@Nombre
				   ,@Contrasena
				   ,1
				   ,@IdUsuarioActual
				   ,GETDATE())

			SET @log = CONCAT('Usuario ', @Id, ' creado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Usuario creado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al registrar usuario ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al crear el usuario.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--ACTUALIZACIÓN DEL PAGO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 2)
	BEGIN
		BEGIN TRY
			UPDATE [seguridad].[Usuarios]
			   SET [IdActor] = ISNULL(@IdActor, [IdActor])
				  ,[IdTipoActor] = ISNULL(@IdTipoActor, [IdActor])
				  ,[Nombre] = ISNULL(@Nombre, [Nombre])
				  ,[Contrasena] = ISNULL(@Contrasena, [Contrasena])
				  ,[IdEstatus] = ISNULL(@IdEstatus, [IdEstatus])
				  ,[IdUsuarioModifica] = @IdUsuarioActual
				  ,[FechaModificacion] = GETDATE()
			 WHERE Id = @Id

			SET @log = CONCAT('Usuario ', @Id, ' actualizado correctamente.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'true' AS Estatus
				,@Id AS Id
				, 'Usuario actualizado.' AS Observacion
		END TRY
		BEGIN CATCH
			SET @log = CONCAT('Error al actualizar usuario ', @Id, '.') 
			EXEC [seguridad].[SPLog] @log, @IdUsuarioActual
			SELECT 'false' AS Estatus
				,@Id AS Id
				, 'Error al actualizar usuario.' AS Observacion
			EXECUTE usp_GetErrorInfo;  
		END CATCH
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR USUARIO
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 3)
	BEGIN
		SELECT [Usuarios].[Id]
			  ,[Usuarios].[IdActor]
			  ,[Usuarios].[IdTipoActor]
			  ,[Usuarios].[Nombre]
			  ,[Usuarios].[Contrasena]
			  ,[Usuarios].[IdEstatus]
			  ,[Usuarios].[IdUsuarioCrea]
			  ,[Usuarios].[FechaCreacion]
			  ,[Usuarios].[IdUsuarioModifica]
			  ,[Usuarios].[FechaModificacion]
		  FROM [seguridad].[Usuarios] AS [Usuarios]
		  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [Usuarios].[IdTipoActor]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Usuarios].[IdEstatus]
		  WHERE [Usuarios].[Id] = ISNULL(@Id, [Usuarios].[Id])
		  AND [Usuarios].[IdTipoActor] = ISNULL(@IdTipoActor, [Usuarios].[IdTipoActor])
		  AND [Usuarios].[Nombre] = ISNULL(@Nombre, [Usuarios].[Nombre])
		  AND [Usuarios].[IdEstatus] != 2 
	END
	-------------------------------------------------------------------------------------------
	--CONSULTAR LOGIN
	-------------------------------------------------------------------------------------------
	IF (@Opcion = 4)
	BEGIN
		SELECT [Usuarios].[Id]
			  ,[Usuarios].[IdActor]
			  ,[Usuarios].[IdTipoActor]
			  ,[Usuarios].[Nombre]
			  ,[Usuarios].[Contrasena]
			  ,[Usuarios].[IdEstatus]
			  ,[Usuarios].[IdUsuarioCrea]
			  ,[Usuarios].[FechaCreacion]
			  ,[Usuarios].[IdUsuarioModifica]
			  ,[Usuarios].[FechaModificacion]
		  FROM [seguridad].[Usuarios] AS [Usuarios]
		  INNER JOIN [catalogos].[TiposActores] AS [TiposActores] ON [TiposActores].[Id] = [Usuarios].[IdTipoActor]
		  INNER JOIN [catalogos].[Estatus] AS [Estatus] ON [Estatus].[Id] = [Usuarios].[IdEstatus]
		  WHERE [Usuarios].[Nombre] = @Nombre
		  AND [Usuarios].[Contrasena] = @Contrasena
		  AND [Usuarios].[IdEstatus] != 2 
	END
END


GO
USE [master]
GO
ALTER DATABASE [diazfu] SET  READ_WRITE 
GO
