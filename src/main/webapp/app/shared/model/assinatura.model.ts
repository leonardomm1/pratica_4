import { IPadaria } from 'app/shared/model/padaria.model';
import { IUser } from 'app/shared/model/user.model';

export interface IAssinatura {
  id?: number;
  valor?: number;
  nome?: string;
  quantidadeDias?: number;
  ativa?: boolean;
  pagamentoRecorrenciaId?: number;
  fotoContentType?: string | null;
  foto?: string | null;
  padaria?: IPadaria | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IAssinatura> = {
  ativa: false,
};
