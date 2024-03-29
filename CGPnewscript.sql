USE [master]
GO
/****** Object:  Database [CafeGocPho]    Script Date: 22-Feb-23 20:21:06 ******/
CREATE DATABASE [CafeGocPho]
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
/****** Object:  Table [dbo].[Account]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[AccountFeature]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[Customer]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[Employee]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[Feature]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[Group]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[Import]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[ImportDetails]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[Market]    Script Date: 22-Feb-23 20:21:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Market](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](500) NULL,
	[date] [date] NULL,
	[total] [int] NULL,
	[note] [nvarchar](500) NULL,
 CONSTRAINT [PK_Market] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 22-Feb-23 20:21:07 ******/
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
	[paid] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 22-Feb-23 20:21:07 ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 22-Feb-23 20:21:07 ******/
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
INSERT [dbo].[Account] ([username], [password], [employeeid], [right]) VALUES (N'admin', N'admin', NULL, N'admin')
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
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 24)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 25)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'admin', 26)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'emp', 19)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'emp', 20)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'emp', 22)
INSERT [dbo].[AccountFeature] ([right], [fid]) VALUES (N'emp', 23)
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([id], [customerNo], [cname], [gender], [dob], [phone], [email], [address], [disable]) VALUES (1, NULL, N'khách lẻ', NULL, NULL, NULL, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Customer] OFF
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
INSERT [dbo].[Feature] ([fid], [url]) VALUES (23, N'/emp/sell-history')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (24, N'/market')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (25, N'/market/insert')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (26, N'/market/update')
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

SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (1, NULL, N'Cafe đen', NULL, 0, 20000, 1, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (2, NULL, N'Cafe sữa', NULL, 0, 20000, 1, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (3, NULL, N'Cafe mật ong', NULL, 0, 25000, 1, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (4, NULL, N'Cafe cacao', NULL, 0, 25000, 1, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (5, NULL, N'Cacao sữa', NULL, 0, 25000, 1, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (6, NULL, N'Bạc xỉu', NULL, 0, 25000, 1, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (7, NULL, N'Nước ép cam', NULL, 0, 30000, 2, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (8, NULL, N'Nước ép bưởi', NULL, 0, 30000, 2, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (9, NULL, N'Nước ép dứa', NULL, 0, 25000, 2, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (10, NULL, N'Nước ép ổi', NULL, 0, 25000, 2, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (11, NULL, N'Nước ép dưa hấu', NULL, 0, 25000, 2, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (12, NULL, N'Nước ép táo', NULL, 0, 25000, 2, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (13, NULL, N'Trà đào lipton', NULL, 0, 15000, 3, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (14, NULL, N'Trà chanh mật ong', NULL, 0, 20000, 3, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (15, NULL, N'Trà tắc mật ong', NULL, 0, 20000, 3, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (16, NULL, N'Trà chanh mật ong sả', NULL, 0, 20000, 3, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (17, NULL, N'Trà chanh mật ong bạc hà', NULL, 0, 20000, 3, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (18, NULL, N'Trà gừng mật ong', NULL, 0, 20000, 3, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (19, NULL, N'Nước chanh tươi', NULL, 0, 15000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (20, NULL, N'Nước chanh đào mật ong', NULL, 0, 25000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (21, NULL, N'Nước dâu', NULL, 0, 25000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (22, NULL, N'Nước mơ', NULL, 0, 25000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (23, NULL, N'Nước dừa tươi (lạnh)', NULL, 0, 20000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (25, NULL, N'Nước dừa tươi (thường)', NULL, 0, 18000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (26, NULL, N'Nước sấu', NULL, 0, 25000, 4, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (27, NULL, N'Sữa chua đánh đá', NULL, 0, 25000, 5, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (28, NULL, N'Sữa chua cafe', NULL, 0, 25000, 5, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (29, NULL, N'Sinh tố bơ', NULL, 0, 35000, 6, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (30, NULL, N'Sinh tố xoài', NULL, 0, 30000, 6, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (31, NULL, N'Trà sữa trân châu', NULL, 0, 25000, 7, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (32, NULL, N'Trà sữa Thái (xanh)', NULL, 0, 10000, 7, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (33, NULL, N'Trà sữa Thái (đỏ)', NULL, 0, 10000, 7, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (34, NULL, N'Cà phê nguyên chất 1kg', NULL, 0, 250000, 8, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (35, NULL, N'Mật ong hoa cafe 1L', NULL, 0, 180000, 8, NULL)
INSERT [dbo].[Product] ([id], [productNo], [pname], [quantity], [priceImport], [priceExport], [gid], [disable]) VALUES (37, NULL, N'Mật ong hoa cafe 500ml', NULL, 0, 95000, 8, NULL)
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
