import './home.scss';

import React, { useState } from 'react';

import { Row, Col, Card, CardBody, CardTitle, CardSubtitle, CardText, Fade, Container, CardColumns } from 'reactstrap';

import { useAppSelector } from 'app/config/store';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);
  const [isOpen] = useState(false);
  const imgFolder = '../../../content/images/';

  return (
    <Row>
      <Col md="12" className="pad">
        <span className="hipster rounded" />
      </Col>
      <Col md="12 center">
        <h2>
          Nada melhor que acordar com
          <br />
          um pãozinho quente!
        </h2>
        <p className="lead">Já pensou receber todos os dias</p>
      </Col>
      <Row>
        <h1 className="center">Veja e aprecie nossos produtos!</h1>
        <Col md="3">
          <Card
            style={{
              width: '18rem',
            }}
          >
            <img alt="Sample" height={'169px'} src={imgFolder + 'bomba-chocolate.jpg'} />
            <CardBody>
              <CardTitle tag="h5">Bomba de Chocolate </CardTitle>
              <CardSubtitle className="mb-2 text-muted" tag="h6">
                4 vezes/semana
              </CardSubtitle>
              <CardText>Que tal um docinho?</CardText>
            </CardBody>
          </Card>
        </Col>
        <Col md="3">
          <Card
            style={{
              width: '18rem',
            }}
          >
            <img alt="Sample" src={imgFolder + 'pao-caseiro.jpg'} />
            <CardBody>
              <CardTitle tag="h5">Pão Fresco</CardTitle>
              <CardSubtitle className="mb-2 text-muted" tag="h6">
                2 vezes/semana
              </CardSubtitle>
              <CardText>Que tal receber em sua casa, na sua hora? :)</CardText>
            </CardBody>
          </Card>
        </Col>
        <Col md="3">
          <Card
            style={{
              width: '18rem',
            }}
          >
            <img alt="Sample" src={imgFolder + 'pao-forma-caseiro.jpg'} />
            <CardBody>
              <CardTitle tag="h5">Pão de Forma</CardTitle>
              <CardSubtitle className="mb-2 text-muted" tag="h6">
                Todos os dias
              </CardSubtitle>
              <CardText>Quanto pão!</CardText>
            </CardBody>
          </Card>
        </Col>
        <Col md="3">
          <Card
            style={{
              width: '18rem',
            }}
          >
            <img alt="Sample" height={'169px'} src={imgFolder + 'rosquinhas.webp'} />
            <CardBody>
              <CardTitle tag="h5">Rosquinhas</CardTitle>
              <CardSubtitle className="mb-2 text-muted" tag="h6">
                Todos os dias
              </CardSubtitle>
              <CardText>Colorido!</CardText>
            </CardBody>
          </Card>
        </Col>
      </Row>
      <Col lg="6">
        <Row>
          <h2>O que oferecemos?</h2>
          <div>
            Um plano de assinatura para sua padaria favorita! Imagina poder escolher uma cesta de qualquer produto que a padaria tem a
            oferecer. Monte sua cesta, crie seu plano ou escolha a sua vontade e receba no momento que você quiser.
          </div>
          <Col md="4">
            <div>Produtos de qualidade!</div>
          </Col>
          <Col md="4">
            <div>Entrega na sua casa!</div>
          </Col>
          <Col md="4">
            <div>Cesta personalizada!</div>
          </Col>
        </Row>
      </Col>
      {/* {account?.login ? (
          <div>
            <Alert color="success">Você está logado como &quot;{account.login}&quot;.</Alert>
          </div>
        ) : (
          <div>
            <Alert color="warning">
              Se deseja
              <span>&nbsp;</span>
              <Link to="/login" className="alert-link">
                entrar
              </Link>
              , utilize as seguintes contas padrões:
              <br />- Administrador (usuário=&quot;admin&quot; and senha=&quot;admin&quot;) <br />- Usuário (usuário=&quot;user&quot; e
              senha=&quot;user&quot;).
            </Alert>

            <Alert color="warning">
              Não possui uma conta ainda?&nbsp;
              <Link to="/account/register" className="alert-link">
                Crie uma nova conta
              </Link>
            </Alert>
          </div>
        )}
        <p>Em caso de dúvida sobre o JHipster:</p>

        <ul>
          <li>
            <a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">
              Página principal JHipster
            </a>
          </li>
          <li>
            <a href="https://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener noreferrer">
              JHipster no Stack Overflow
            </a>
          </li>
          <li>
            <a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener noreferrer">
              JHipster bug tracker
            </a>
          </li>
          <li>
            <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
              Sala de bate-papo pública JHipster
            </a>
          </li>
          <li>
            <a href="https://twitter.com/jhipster" target="_blank" rel="noopener noreferrer">
              siga @jhipster no Twitter
            </a>
          </li>
        </ul>

        <p>
          Se você gosta do JHipster, não se esqueça de avaliar no{' '}
          <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
            GitHub
          </a>
          !
        </p> */}
    </Row>
  );
};

export default Home;
