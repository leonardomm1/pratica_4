import { IUser } from 'app/shared/model/user.model';

export interface IPadaria {
  id?: number;
  nome?: string;
  fantasia?: string;
  telefone?: string;
  cnpj?: string;
  cep?: string;
  rua?: string;
  bairro?: string;
  estado?: string;
  numero?: string;
  complemento?: string | null;
  contato?: string | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IPadaria> = {};
