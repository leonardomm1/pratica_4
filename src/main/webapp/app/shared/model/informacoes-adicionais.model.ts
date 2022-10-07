import { IUser } from 'app/shared/model/user.model';

export interface IInformacoesAdicionais {
  id?: number;
  telefone?: string;
  cpf?: string;
  cep?: string;
  rua?: string;
  bairro?: string;
  estado?: string;
  numero?: string;
  complemento?: string | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IInformacoesAdicionais> = {};
