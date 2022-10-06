import './home.scss';

import React, { useState } from 'react';
import { Link } from 'react-router-dom';

import {
  Row,
  Col,
  Alert,
  Card,
  CardBody,
  CardTitle,
  CardSubtitle,
  CardText,
  Carousel,
  CarouselItem,
  CarouselControl,
  CarouselIndicators,
  CarouselCaption,
} from 'reactstrap';

import { useAppSelector } from 'app/config/store';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);
  const [activeIndex, setActiveIndex] = useState(0);

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
        <h1>Mais cards aqui para produtos</h1>
      </Col>
      <Col md="3"></Col>
      <Col md="3">
        <Card
          style={{
            width: '18rem',
          }}
        >
          <img alt="Sample" src="../../../content/images/pao-caseiro.jpg" />
          <CardBody>
            <CardTitle tag="h5">Pão Fresco</CardTitle>
            <CardSubtitle className="mb-2 text-muted" tag="h6">
              2 vezes/semana
            </CardSubtitle>
            <CardText>Que tal receber em sua casa, na sua hora? :)</CardText>
          </CardBody>
        </Card>
      </Col>
      <Col md="6">
        <Card
          style={{
            width: '18rem',
          }}
        >
          <img alt="Sample" src="../../../content/images/pao-forma-caseiro.jpg" />
          <CardBody>
            <CardTitle tag="h5">Pão de Forma</CardTitle>
            <CardSubtitle className="mb-2 text-muted" tag="h6">
              Todos os dias
            </CardSubtitle>
            <CardText>Quanto pão!</CardText>
          </CardBody>
        </Card>
      </Col>
      <h1>Sobre nós, explicar o produto</h1>

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
