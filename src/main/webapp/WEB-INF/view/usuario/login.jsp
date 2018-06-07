<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AudioManager - Login</title>
        <link rel="stylesheet" href="../styles/bulma.css"/>
        <link rel="stylesheet" href="../styles/style.css"/>
        <link rel="stylesheet" href="../styles/login.css"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha256-eZrrJcwDc/3uDhsdt61sL2oOBY362qM3lon1gyExkL0=" crossorigin="anonymous" />
    </head>
    <body>
        <section class="hero is-success is-fullheight">
            <div class="hero-head">
                <nav class="navbar">
                    <div class="container">
                        <div class="navbar-brand">
                            <a class="navbar-item" href="../">
                                <img src="../img/logo.png" alt="Logo">
                                <h1 class="title">AudioManager</h1>
                            </a>
                            <span class="navbar-burger burger" data-target="navbarMenu">
                                <span></span>
                                <span></span>
                                <span></span>
                            </span>
                        </div>
                        <div id="navbarMenu" class="navbar-menu">
                            <div class="navbar-end">
                                <span class="navbar-item">
                                    <a class="button is-white is-outlined" href="../">
                                        <span class="icon">
                                            <i class="fa fa-home"></i>
                                        </span>
                                        <span>Home</span>
                                    </a>
                                </span>
                                <span class="navbar-item">
                                    <a class="button is-white is-outlined" href="cadastro">
                                        <span class="icon">
                                            <i class="fa fa-user-plus"></i>
                                        </span>
                                        <span>Cadastrar</span>
                                    </a>
                                </span>
                                <span class="navbar-item">
                                    <a class="button is-white is-outlined" href="https://github.com/josevictorferreira/audiomanager">
                                        <span class="icon">
                                            <i class="fa fa-github"></i>
                                        </span>
                                        <span>View Source</span>
                                    </a>
                                </span>
                            </div>
                        </div>
                </nav>
            </div>
            <div class="hero-body">
                <div class="container has-text-centered">
                    <div class="column is-4 is-offset-4">
                        <h3 class="title has-text-white">Login</h3>
                        <p class="subtitle has-text-white">Por favor, execute o login na plataforma.</p>
                        <div class="box">  
                            <c:if test="${sessionScope.suMessage != null}">
                                <div class="notification is-success">
                                    <strong>Erro</strong>
                                    <c:out value='${sessionScope.suMessage}'/>
                                    <c:set var="suMessage" value="" scope="session"  />
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.erMessage != null}">
                                <div class="notification is-danger">
                                    <strong>Erro</strong>
                                    <c:out value='${sessionScope.erMessage}'/>
                                    <c:set var="erMessage" value="" scope="session"  />
                                </div>
                            </c:if>
                            <form action="entrar" method="POST">
                                <div class="field">
                                    <div class="control">
                                        <input class="input is-large" type="email" placeholder="Email" autofocus="" name="email">
                                    </div>
                                </div>

                                <div class="field">
                                    <div class="control">
                                        <input class="input is-large" type="password" placeholder="Senha" name="senha">
                                    </div>
                                </div>
                                <button class="button is-block is-info is-large is-fullwidth">Login</button>
                            </form>
                        </div>
                        <p class="has-text-white">
                            <a href="cadastro">Cadastrar</a>
                        </p>
                    </div>
                </div>
            </div>
        </section>
        <script async type="text/javascript" src="../js/bulma.js"></script>
    </body>
</html>
