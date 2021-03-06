USE [master]
GO
/****** Object:  Database [RentBook]    Script Date: 8/19/2020 10:30:04 PM ******/
CREATE DATABASE [RentBook]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'RentBook', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\RentBook.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'RentBook_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\RentBook_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [RentBook] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [RentBook].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [RentBook] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [RentBook] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [RentBook] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [RentBook] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [RentBook] SET ARITHABORT OFF 
GO
ALTER DATABASE [RentBook] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [RentBook] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [RentBook] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [RentBook] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [RentBook] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [RentBook] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [RentBook] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [RentBook] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [RentBook] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [RentBook] SET  DISABLE_BROKER 
GO
ALTER DATABASE [RentBook] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [RentBook] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [RentBook] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [RentBook] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [RentBook] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [RentBook] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [RentBook] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [RentBook] SET RECOVERY FULL 
GO
ALTER DATABASE [RentBook] SET  MULTI_USER 
GO
ALTER DATABASE [RentBook] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [RentBook] SET DB_CHAINING OFF 
GO
ALTER DATABASE [RentBook] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [RentBook] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [RentBook] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'RentBook', N'ON'
GO
USE [RentBook]
GO
/****** Object:  Table [dbo].[Book]    Script Date: 8/19/2020 10:30:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Book](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[Price] [float] NOT NULL,
	[Author] [nvarchar](100) NOT NULL,
	[CategoryId] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Details] [nvarchar](250) NOT NULL,
	[CreateAt] [datetime] NULL,
	[UpdateAt] [datetime] NULL,
	[StatusActive] [bit] NOT NULL,
 CONSTRAINT [PK_Book] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Category]    Script Date: 8/19/2020 10:30:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[CreateAt] [datetime] NULL,
	[UpdateAt] [datetime] NULL,
	[StatusActive] [bit] NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Order]    Script Date: 8/19/2020 10:30:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[CreateAt] [datetime] NULL,
	[UpdateAt] [datetime] NULL,
	[StatusActive] [bit] NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 8/19/2020 10:30:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NOT NULL,
	[BookId] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[CreateAt] [datetime] NULL,
	[UpdateAt] [datetime] NULL,
	[StatusActive] [bit] NOT NULL,
 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Role]    Script Date: 8/19/2020 10:30:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NOT NULL,
	[CreateAt] [datetime] NULL,
	[UpdateAt] [datetime] NULL,
	[StatusActive] [bit] NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 8/19/2020 10:30:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](100) NOT NULL,
	[Password] [nvarchar](max) NOT NULL,
	[Fullname] [nvarchar](100) NOT NULL,
	[Email] [nvarchar](100) NOT NULL,
	[CreateAt] [datetime] NULL,
	[UpdateAt] [datetime] NULL,
	[StatusActive] [bit] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserRole]    Script Date: 8/19/2020 10:30:04 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserRole](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[RoleId] [int] NOT NULL,
	[CreateAt] [datetime] NULL,
	[UpdateAt] [datetime] NULL,
	[StatusActive] [bit] NOT NULL,
 CONSTRAINT [PK_UserRole] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([Id], [Name], [CreateAt], [UpdateAt], [StatusActive]) VALUES (2, N'admin', NULL, NULL, 1)
INSERT [dbo].[Role] ([Id], [Name], [CreateAt], [UpdateAt], [StatusActive]) VALUES (3, N'user', NULL, NULL, 1)
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [CreateAt], [UpdateAt], [StatusActive]) VALUES (5, N'thanhpm', N'$2a$10$3X2ZvxkUGB4yqaJ0vQMj.u1FbebpMVymISAnEZFSc.vWHjyfWxdJi', N'thanhpm', N'thanh@gmail.com', CAST(N'2020-08-16 10:37:08.287' AS DateTime), NULL, 1)
INSERT [dbo].[User] ([Id], [Username], [Password], [Fullname], [Email], [CreateAt], [UpdateAt], [StatusActive]) VALUES (6, N'duongdd', N'$2a$10$S65kogqTt1AsP973P.Temu3uOuRt0g7UUNDpsKXkr2P0CQPvkrL2S', N'ducduong', N'duong@gmail.com', CAST(N'2020-08-18 21:43:46.123' AS DateTime), NULL, 1)
SET IDENTITY_INSERT [dbo].[User] OFF
SET IDENTITY_INSERT [dbo].[UserRole] ON 

INSERT [dbo].[UserRole] ([Id], [UserId], [RoleId], [CreateAt], [UpdateAt], [StatusActive]) VALUES (1, 5, 3, NULL, NULL, 1)
INSERT [dbo].[UserRole] ([Id], [UserId], [RoleId], [CreateAt], [UpdateAt], [StatusActive]) VALUES (4, 5, 2, NULL, NULL, 1)
INSERT [dbo].[UserRole] ([Id], [UserId], [RoleId], [CreateAt], [UpdateAt], [StatusActive]) VALUES (5, 6, 3, CAST(N'2020-08-18 21:43:46.287' AS DateTime), NULL, 1)
SET IDENTITY_INSERT [dbo].[UserRole] OFF
ALTER TABLE [dbo].[Book]  WITH CHECK ADD  CONSTRAINT [FK_Book_Category] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[Category] ([Id])
GO
ALTER TABLE [dbo].[Book] CHECK CONSTRAINT [FK_Book_Category]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_User]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Book] FOREIGN KEY([BookId])
REFERENCES [dbo].[Book] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Book]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Order] FOREIGN KEY([OrderId])
REFERENCES [dbo].[Order] ([Id])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Order]
GO
ALTER TABLE [dbo].[UserRole]  WITH CHECK ADD  CONSTRAINT [FK_UserRole_Role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[Role] ([Id])
GO
ALTER TABLE [dbo].[UserRole] CHECK CONSTRAINT [FK_UserRole_Role]
GO
ALTER TABLE [dbo].[UserRole]  WITH CHECK ADD  CONSTRAINT [FK_UserRole_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[UserRole] CHECK CONSTRAINT [FK_UserRole_User]
GO
USE [master]
GO
ALTER DATABASE [RentBook] SET  READ_WRITE 
GO
