# -*- encoding: utf-8 -*-

from app.home import blueprint
from flask import jsonify, render_template, request, url_for, redirect
from flask_login import login_required, current_user
from jinja2 import TemplateNotFound
from sqlalchemy.orm.attributes import flag_modified

from app import db
from app.base.models import User


@blueprint.route('/')
@login_required
def index():
    user = User.query.filter_by(username=current_user.username).first()
    empty = ['', None]
    if current_user.name in empty or current_user.surname in empty:
        return render_template('completar_cadastro.html')
    return render_template('home.html')


@blueprint.route('/<template>')
@login_required
def route_template(template):
    return render_template(template + '.html')


@blueprint.route('/assinatura_paes')
@login_required
def assinatura_paes():
    return render_template('assinatura_paes.html')


@blueprint.route('/completar_cadastro_usuario', methods=['GET', 'POST'])
@login_required
def completar_cadastro():

    user_extra = User(**request.form)
    user = User.query.filter_by(username=current_user.username).first()

    a = list(user.__dict__.keys())
    b = list(user.__dict__.values())
    user_ = dict(zip(a, b))

    new_user = {}
    avoid = ['_sa_instance_state', '', None, 'password']

    for k, v in user_.items():
        if k not in avoid and type(k) is str:
            new_user[k] = v
    
    a = list(user_extra.__dict__.keys())
    b = list(user_extra.__dict__.values())
    user_extra = dict(zip(a, b))
    
    for k, v in user_extra.items():
        if k not in avoid and type(k) is str:
            new_user[k] = v
    
    for k, v in new_user.items():
        if v is not None:
            v = str(v).strip()
        user.__dict__[k] = v
        flag_modified(user, k)
    
    db.session.merge(user)
    db.session.flush()
    db.session.commit()

    return redirect(url_for('home_blueprint.index'))

