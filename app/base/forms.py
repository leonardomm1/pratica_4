from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField

## login and registration


class LoginForm(FlaskForm):
    username = StringField('Celular', id='username_login')
    password = PasswordField('Senha', id='pwd_login')


class CreateAccountForm(FlaskForm):
    username = StringField('Celular', id='username_create')
    email = StringField('E-mail')
    password = PasswordField('Senha', id='pwd_create')
