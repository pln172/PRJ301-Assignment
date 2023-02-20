USE [master]
GO
/****** Object:  Database [CafeGocPho]    Script Date: 20-Feb-23 21:31:42 ******/
CREATE DATABASE [CafeGocPho]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CafeGocPho', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\CafeGocPho.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CafeGocPho_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\CafeGocPho_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [CafeGocPho] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CafeGocPho].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CafeGocPho] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CafeGocPho] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CafeGocPho] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CafeGocPho] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CafeGocPho] SET ARITHABORT OFF 
GO
ALTER DATABASE [CafeGocPho] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CafeGocPho] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CafeGocPho] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CafeGocPho] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CafeGocPho] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CafeGocPho] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CafeGocPho] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CafeGocPho] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CafeGocPho] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CafeGocPho] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CafeGocPho] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CafeGocPho] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CafeGocPho] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CafeGocPho] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CafeGocPho] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CafeGocPho] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CafeGocPho] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CafeGocPho] SET RECOVERY FULL 
GO
ALTER DATABASE [CafeGocPho] SET  MULTI_USER 
GO
ALTER DATABASE [CafeGocPho] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CafeGocPho] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CafeGocPho] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CafeGocPho] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [CafeGocPho] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [CafeGocPho] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'CafeGocPho', N'ON'
GO
ALTER DATABASE [CafeGocPho] SET QUERY_STORE = OFF
GO
USE [CafeGocPho]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [varchar](300) NOT NULL,
	[password] [varchar](300) NOT NULL,
	[employeeid] [int] NULL,
	[right] [varchar](50) NULL,
 CONSTRAINT [PK_Account_1] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AccountFeature]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountFeature](
	[right] [varchar](300) NOT NULL,
	[fid] [int] NOT NULL,
 CONSTRAINT [PK_AccountFeature] PRIMARY KEY CLUSTERED 
(
	[right] ASC,
	[fid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[customerNo] [varchar](500) NULL,
	[cname] [nvarchar](500) NULL,
	[gender] [bit] NULL,
	[dob] [date] NULL,
	[phone] [varchar](10) NULL,
	[email] [varchar](500) NULL,
	[address] [nvarchar](500) NULL,
	[disable] [bit] NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ename] [nvarchar](500) NULL,
	[gender] [bit] NULL,
	[dob] [date] NULL,
	[phone] [varchar](10) NULL,
	[email] [varchar](500) NULL,
	[address] [nvarchar](500) NULL,
	[starting_date] [date] NULL,
	[leaving_date] [date] NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feature]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feature](
	[fid] [int] IDENTITY(1,1) NOT NULL,
	[url] [varchar](500) NOT NULL,
 CONSTRAINT [PK_Feature] PRIMARY KEY CLUSTERED 
(
	[fid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[gname] [nvarchar](300) NOT NULL,
	[disable] [bit] NULL,
 CONSTRAINT [PK_Group] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Import]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Import](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[importNo] [varchar](500) NULL,
	[date] [datetime] NULL,
	[itotal] [int] NULL,
 CONSTRAINT [PK_Import] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ImportDetails]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ImportDetails](
	[iid] [int] NOT NULL,
	[pid] [int] NOT NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
	[total] [int] NULL,
 CONSTRAINT [PK_ImportDetail] PRIMARY KEY CLUSTERED 
(
	[iid] ASC,
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[orderNo] [varchar](500) NULL,
	[eid] [int] NULL,
	[cid] [int] NULL,
	[date] [datetime] NULL,
	[ototal] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[oid] [int] NOT NULL,
	[pid] [int] NOT NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
	[total] [int] NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[oid] ASC,
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 20-Feb-23 21:31:42 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productNo] [varchar](500) NULL,
	[pname] [nvarchar](500) NULL,
	[quantity] [int] NULL,
	[priceImport] [int] NULL,
	[priceExport] [int] NULL,
	[gid] [int] NOT NULL,
	[disable] [bit] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([username], [password], [employeeid], [right]) VALUES (N'sa', N'123', NULL, N'admin')
INSERT [dbo].[Account] ([username], [password], [employeeid], [right]) VALUES (N'sonmt', N'sonmt', 1, N'emp')
GO
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 1)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 2)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 3)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 4)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 5)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 6)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 7)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 8)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 9)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 10)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 11)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 12)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 13)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 14)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 15)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 16)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 17)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 18)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 19)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 20)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 21)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'emp', 19)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'emp', 20)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'emp', 22)
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [customerNo], [cname], [gender], [dob], [phone], [email], [address], [disable]) VALUES (1, NULL, N'khách lẻ', NULL, NULL, NULL, NULL, NULL, NULL)
INSERT [dbo].[Customer] ([id], [customerNo], [cname], [gender], [dob], [phone], [email], [address], [disable]) VALUES (2, NULL, N'Loann', 1, CAST(N'2000-02-17' AS Date), N'0111111111', N'phuongloan517@gmail.com', N'An Dương, Tây Hồ, Hà Nội', NULL)
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Employee] ON 

INSERT [dbo].[Employee] ([id], [ename], [gender], [dob], [phone], [email], [address], [starting_date], [leaving_date], [active]) VALUES (1, N'Sơn', 1, CAST(N'1996-06-02' AS Date), N'0123456789', N'', N'', CAST(N'2023-02-19' AS Date), NULL, 1)
INSERT [dbo].[Employee] ([id], [ename], [gender], [dob], [phone], [email], [address], [starting_date], [leaving_date], [active]) VALUES (2, N'Hê', 0, CAST(N'2001-01-30' AS Date), N'0987654321', N'', N'', CAST(N'2023-02-20' AS Date), CAST(N'2023-02-20' AS Date), 0)
INSERT [dbo].[Employee] ([id], [ename], [gender], [dob], [phone], [email], [address], [starting_date], [leaving_date], [active]) VALUES (3, N'Ấn', 1, CAST(N'2005-02-15' AS Date), N'0654444444', N'', N'', CAST(N'2023-02-20' AS Date), CAST(N'2023-02-20' AS Date), 0)
SET IDENTITY_INSERT [dbo].[Employee] OFF
GO
SET IDENTITY_INSERT [dbo].[Feature] ON 

INSERT [dbo].[Feature] ([fid], [url]) VALUES (1, N'/report')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (2, N'/statistic')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (3, N'/customer')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (4, N'/customer/insert')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (5, N'/customer/update')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (6, N'/employee/detail')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (7, N'/employee')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (8, N'/employee/insert')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (9, N'/employee/update')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (10, N'/history')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (11, N'/history/import')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (12, N'/history/import/detail')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (13, N'/history/sale')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (14, N'/history/sale/detail')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (15, N'/import')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (16, N'/product/insert')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (17, N'/product')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (18, N'/product/update')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (19, N'/sell')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (20, N'/logout')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (21, N'/adminloginafter')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (22, N'/emp/sell')
SET IDENTITY_INSERT [dbo].[Feature] OFF
GO
SET IDENTITY_INSERT [dbo].[Group] ON 

INSERT [dbo].[Group] ([id], [gname], [disable]) VALUES (1, N'Cà phê/Cacao', NULL)
INSERT [dbo].[Group] ([id], [gname], [disable]) VALUES (2, N'Nước ép', NULL)
INSERT [dbo].[Group] ([id], [gname], [disable]) VALUES (3, N'Trà', NULL)
INSERT [dbo].[Group] ([id], [gname], [disable]) VALUES (4, N'Các loại nước', NULL)
INSERT [dbo].[Group] ([id], [gname], [disable]) VALUES (5, N'Sữa chua', NULL)
INSERT [dbo].[Group] ([id], [gname], [disable]) VALUES (6, N'Sinh tố', NULL)
INSERT [dbo].[Group] ([id], [gname], [disable]) VALUES (7, N'Trà sữa', NULL)
INSERT [dbo].[Group] ([id], [gname], [disable]) VALUES (8, N'Khác', NULL)
SET IDENTITY_INSERT [dbo].[Group] OFF
GO
SET IDENTITY_INSERT [dbo].[Import] ON 

INSERT [dbo].[Import] ([id], [importNo], [date], [itotal]) VALUES (5, NULL, CAST(N'2023-02-20T00:45:24.377' AS DateTime), 0)
INSERT [dbo].[Import] ([id], [importNo], [date], [itotal]) VALUES (6, NULL, CAST(N'2023-02-20T00:47:20.173' AS DateTime), 100000)
SET IDENTITY_INSERT [dbo].[Import] OFF
GO
INSERT [dbo].[ImportDetails] ([iid], [pid], [quantity], [price], [total]) VALUES (5, 16, 5, 0, 0)
INSERT [dbo].[ImportDetails] ([iid], [pid], [quantity], [price], [total]) VALUES (6, 16, 1, 100000, 100000)
GO
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (5, NULL, NULL, 1, CAST(N'2023-02-18T21:47:53.137' AS DateTime), 70000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (6, NULL, NULL, 1, CAST(N'2023-02-18T23:55:55.527' AS DateTime), 140000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (7, NULL, NULL, 1, CAST(N'2023-02-19T00:05:04.583' AS DateTime), 90000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (8, NULL, 1, 1, CAST(N'2023-02-19T22:07:16.990' AS DateTime), 20000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (9, NULL, 1, 1, CAST(N'2023-02-19T22:13:18.027' AS DateTime), 20000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (10, NULL, 1, 1, CAST(N'2023-02-19T22:15:20.313' AS DateTime), 20000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (11, NULL, 1, 1, CAST(N'2023-02-19T22:16:29.843' AS DateTime), 20000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (12, NULL, 1, 1, CAST(N'2023-02-19T22:22:37.267' AS DateTime), 20000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (13, NULL, 1, 1, CAST(N'2023-02-19T22:26:00.377' AS DateTime), 40000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (14, NULL, 1, 1, CAST(N'2023-02-20T00:35:00.847' AS DateTime), 30000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (15, NULL, NULL, 1, CAST(N'2023-02-20T00:44:29.630' AS DateTime), 30000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (16, NULL, 1, 1, CAST(N'2023-02-20T01:13:40.543' AS DateTime), 0)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (17, NULL, 1, 1, CAST(N'2023-02-20T01:15:21.193' AS DateTime), 0)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (18, NULL, 1, 1, CAST(N'2023-02-20T01:16:47.233' AS DateTime), 0)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (19, NULL, 1, 1, CAST(N'2023-02-20T01:17:23.303' AS DateTime), 0)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (20, NULL, 1, 1, CAST(N'2023-02-20T01:20:04.507' AS DateTime), 0)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (21, NULL, 1, 1, CAST(N'2023-02-20T01:24:41.253' AS DateTime), 0)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (22, NULL, 1, 1, CAST(N'2023-02-20T01:25:22.007' AS DateTime), 10000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (23, NULL, 1, 1, CAST(N'2023-02-20T01:30:51.863' AS DateTime), 40000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (24, NULL, 1, 1, CAST(N'2023-02-20T01:32:46.737' AS DateTime), 50000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (25, NULL, 1, 1, CAST(N'2023-02-20T01:50:59.203' AS DateTime), 25000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (26, NULL, 1, 1, CAST(N'2023-02-20T01:52:42.747' AS DateTime), 30000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (27, NULL, 1, 1, CAST(N'2023-02-20T01:55:54.320' AS DateTime), 50000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (28, NULL, 1, 1, CAST(N'2023-02-20T01:58:41.553' AS DateTime), 75000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (29, NULL, 1, 1, CAST(N'2023-02-20T01:59:51.790' AS DateTime), 55000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (30, NULL, 1, 1, CAST(N'2023-02-20T02:01:18.470' AS DateTime), 55000)
INSERT [dbo].[Order] ([id], [orderNo], [eid], [cid], [date], [ototal]) VALUES (31, NULL, 1, 1, CAST(N'2023-02-20T02:01:23.063' AS DateTime), 70000)
SET IDENTITY_INSERT [dbo].[Order] OFF
GO
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (8, 1, 1, 20000, 20000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (9, 1, 1, 20000, 20000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (10, 1, 1, 20000, 20000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (11, 1, 1, 20000, 20000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (12, 1, 1, 20000, 20000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (13, 1, 2, 20000, 40000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (14, 1, 1, 30000, 30000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (15, 1, 1, 30000, 30000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (22, 14, 1, 10000, 10000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (23, 6, 2, 20000, 40000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (24, 10, 2, 25000, 50000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (25, 13, 1, 25000, 25000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (26, 12, 1, 30000, 30000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (27, 9, 2, 25000, 50000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (28, 8, 3, 25000, 75000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (29, 2, 1, 20000, 20000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (29, 13, 1, 25000, 25000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (29, 14, 1, 10000, 10000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (30, 2, 1, 20000, 20000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (30, 13, 1, 25000, 25000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (30, 14, 1, 10000, 10000)
INSERT [dbo].[OrderDetails] ([oid], [pid], [quantity], [price], [total]) VALUES (31, 11, 2, 35000, 70000)
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (1, NULL, N'cafe đen', -5, 0, 30000, 1, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (2, NULL, N'cafe nâu', NULL, NULL, 20000, 1, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (3, NULL, N'nước ép cam', NULL, NULL, 30000, 2, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (4, NULL, N'nước ép dứa', NULL, NULL, 25000, 2, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (5, NULL, N'trà đào lipton', NULL, NULL, 15000, 3, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (6, NULL, N'trà chanh mật ong', NULL, NULL, 20000, 3, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (7, NULL, N'nước chanh tươi', NULL, NULL, 15000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (8, NULL, N'nước chanh đào mật ong', NULL, NULL, 25000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (9, NULL, N'sữa chua đánh đá', NULL, NULL, 25000, 5, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (10, NULL, N'sữa chua cà phê', NULL, NULL, 25000, 5, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (11, NULL, N'sinh tố bơ', NULL, NULL, 35000, 6, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (12, NULL, N'sinh tố xoài', NULL, NULL, 30000, 6, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (13, NULL, N'trà sữa tc', NULL, NULL, 25000, 7, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (14, NULL, N'trà sữa thái ', NULL, NULL, 10000, 7, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (15, NULL, N'cà phê bột', NULL, NULL, 250000, 8, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (16, NULL, N'mật ong', 6, 100000, 200000, 8, NULL)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
ALTER TABLE [dbo].[AccountFeature]  WITH CHECK ADD  CONSTRAINT [FK_AccountFeature_Feature] FOREIGN KEY([fid])
REFERENCES [dbo].[Feature] ([fid])
GO
ALTER TABLE [dbo].[AccountFeature] CHECK CONSTRAINT [FK_AccountFeature_Feature]
GO
ALTER TABLE [dbo].[ImportDetails]  WITH CHECK ADD  CONSTRAINT [FK_ImportDetail_Import] FOREIGN KEY([iid])
REFERENCES [dbo].[Import] ([id])
GO
ALTER TABLE [dbo].[ImportDetails] CHECK CONSTRAINT [FK_ImportDetail_Import]
GO
ALTER TABLE [dbo].[ImportDetails]  WITH CHECK ADD  CONSTRAINT [FK_ImportDetail_Product] FOREIGN KEY([pid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[ImportDetails] CHECK CONSTRAINT [FK_ImportDetail_Product]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Customer] FOREIGN KEY([cid])
REFERENCES [dbo].[Customer] ([id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Customer]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Employee] FOREIGN KEY([eid])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Employee]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([oid])
REFERENCES [dbo].[Order] ([id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetail_Order]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Product] FOREIGN KEY([pid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetail_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Group]
GO
USE [master]
GO
ALTER DATABASE [CafeGocPho] SET  READ_WRITE 
GO
