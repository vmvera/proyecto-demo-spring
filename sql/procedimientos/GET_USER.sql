CREATE PROCEDURE GET_USUARIOS
  -- Add the parameters for the stored procedure here
    @NOMBRE NVARCHAR(50)
AS
  BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    -- Insert statements for procedure here
    SELECT * FROM USUARIOS WHERE NOMBRE LIKE @NOMBRE + '%'
  END
GO

