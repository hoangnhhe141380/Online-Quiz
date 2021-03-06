USE [master]
GO
/****** Object:  Database [OnlineQuiz]    Script Date: 08/03/2021 10:20:10 ******/
CREATE DATABASE [OnlineQuiz] ON  PRIMARY 
( NAME = N'OnlineQuiz', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\OnlineQuiz.mdf' , SIZE = 2304KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'OnlineQuiz_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\OnlineQuiz_log.LDF' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [OnlineQuiz] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [OnlineQuiz].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [OnlineQuiz] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [OnlineQuiz] SET ANSI_NULLS OFF
GO
ALTER DATABASE [OnlineQuiz] SET ANSI_PADDING OFF
GO
ALTER DATABASE [OnlineQuiz] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [OnlineQuiz] SET ARITHABORT OFF
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_CLOSE ON
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [OnlineQuiz] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [OnlineQuiz] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [OnlineQuiz] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [OnlineQuiz] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [OnlineQuiz] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [OnlineQuiz] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [OnlineQuiz] SET  ENABLE_BROKER
GO
ALTER DATABASE [OnlineQuiz] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [OnlineQuiz] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [OnlineQuiz] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [OnlineQuiz] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [OnlineQuiz] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [OnlineQuiz] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [OnlineQuiz] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [OnlineQuiz] SET  READ_WRITE
GO
ALTER DATABASE [OnlineQuiz] SET RECOVERY SIMPLE
GO
ALTER DATABASE [OnlineQuiz] SET  MULTI_USER
GO
ALTER DATABASE [OnlineQuiz] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [OnlineQuiz] SET DB_CHAINING OFF
GO
USE [OnlineQuiz]
GO
/****** Object:  Table [dbo].[role]    Script Date: 08/03/2021 10:20:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] NOT NULL,
	[name] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[role] ([id], [name]) VALUES (1, N'teacher')
INSERT [dbo].[role] ([id], [name]) VALUES (2, N'student')
/****** Object:  Table [dbo].[user]    Script Date: 08/03/2021 10:20:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](30) NOT NULL,
	[password] [nvarchar](max) NOT NULL,
	[roleID] [int] NOT NULL,
	[email] [nvarchar](max) NOT NULL,
	[createdDate] [date] NOT NULL,
	[salt] [varchar](50) NOT NULL,
 CONSTRAINT [PK__user__3213E83F29572725] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[user] ON
INSERT [dbo].[user] ([id], [username], [password], [roleID], [email], [createdDate], [salt]) VALUES (8, N'hoang', N'7cb45e8e4cb5effe5be6b771b68dfb27', 1, N'hoang@gmail.com', CAST(0xD3420B00 AS Date), N'nDX01fFhW52ocLFhpgFGlA==')
INSERT [dbo].[user] ([id], [username], [password], [roleID], [email], [createdDate], [salt]) VALUES (10, N'hoang1', N'1df882270743960682cec40c717e6bd7', 1, N'admin@gmail.com', CAST(0xD3420B00 AS Date), N'u/WX5ReFFV+V3/j4mDZZDg==')
INSERT [dbo].[user] ([id], [username], [password], [roleID], [email], [createdDate], [salt]) VALUES (11, N'user1', N'3f5f9098132b3e7f570aa76bca0a608f', 1, N'hoangdiudang2@gmail.com', CAST(0xD4420B00 AS Date), N'zsFtpi7PkQL73RoKU4HfaQ==')
INSERT [dbo].[user] ([id], [username], [password], [roleID], [email], [createdDate], [salt]) VALUES (12, N'user2', N'aa03faca51c5ddc8d8ed0835136f3d88', 1, N'hoang1@gmail.com', CAST(0xD4420B00 AS Date), N'8ssSfVdspsdkjc/bZdFDcQ==')
INSERT [dbo].[user] ([id], [username], [password], [roleID], [email], [createdDate], [salt]) VALUES (13, N'user3', N'9581528e1c9f9ef288ad8d4b51df7913', 2, N'test@gmail.com', CAST(0xD4420B00 AS Date), N'qECmhE8qH7dKZGykmbFe2Q==')
SET IDENTITY_INSERT [dbo].[user] OFF
/****** Object:  Table [dbo].[question]    Script Date: 08/03/2021 10:20:10 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[question](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[question] [nvarchar](max) NOT NULL,
	[option1] [nvarchar](max) NOT NULL,
	[option2] [nvarchar](max) NOT NULL,
	[option3] [nvarchar](max) NOT NULL,
	[option4] [nvarchar](max) NOT NULL,
	[answers] [nvarchar](50) NOT NULL,
	[userID] [int] NOT NULL,
	[createdDate] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[question] ON
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (15, N'Area of triangle? (Choose 1 answer)', N'a*b', N'a+b', N'2*(a+b)', N'1/2*a*h', N'4', 8, CAST(0xD3420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (16, N'Perimeter of rectangle?(Choose 1 answer)', N'a+b', N'a*b', N'a/b', N'a-b', N'2', 8, CAST(0xD3420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (17, N'Perimter of square (Choose 2 answer)', N'(a+b)*2', N'4*a', N'a*b', N'a/b', N'12', 8, CAST(0xD3420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (18, N'Perimeter of circle? (Choose 1 answer)', N'2*PI*R', N'2*PI*R^2', N'PI*R^3', N'PI*R^2', N'1', 8, CAST(0xD3420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (19, N'Perimeter of triangle? (Choose 1 answer)', N'a+b+c', N'a+b-c', N'a*b*c', N'a-b-c', N'1', 8, CAST(0xD3420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (20, N'Date of today?', N'2/8/2021', N'4/8/2021', N'3/8/2021', N'5/8/2021', N'3', 11, CAST(0xD4420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (21, N'1+1=?', N'2', N'3', N'4', N'1', N'4', 11, CAST(0xD4420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (22, N'3+3=?', N'5', N'6', N'8', N'9', N'2', 11, CAST(0xD4420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (23, N'9+9=?', N'17', N'18', N'19', N'20', N'2', 12, CAST(0xD4420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (27, N'1', N'1', N'1', N'1', N'1', N'1234', 8, CAST(0xD4420B00 AS Date))
INSERT [dbo].[question] ([id], [question], [option1], [option2], [option3], [option4], [answers], [userID], [createdDate]) VALUES (28, N'1', N'2', N'3', N'4', N'5', N'12', 8, CAST(0xD4420B00 AS Date))
SET IDENTITY_INSERT [dbo].[question] OFF
/****** Object:  ForeignKey [FK__user__roleID__2C3393D0]    Script Date: 08/03/2021 10:20:10 ******/
ALTER TABLE [dbo].[user]  WITH CHECK ADD  CONSTRAINT [FK__user__roleID__2C3393D0] FOREIGN KEY([roleID])
REFERENCES [dbo].[role] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[user] CHECK CONSTRAINT [FK__user__roleID__2C3393D0]
GO
/****** Object:  ForeignKey [FK__question__userID__2B3F6F97]    Script Date: 08/03/2021 10:20:10 ******/
ALTER TABLE [dbo].[question]  WITH CHECK ADD  CONSTRAINT [FK__question__userID__2B3F6F97] FOREIGN KEY([userID])
REFERENCES [dbo].[user] ([id])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[question] CHECK CONSTRAINT [FK__question__userID__2B3F6F97]
GO
