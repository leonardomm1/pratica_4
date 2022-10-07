import { IAssinatura } from 'app/shared/model/assinatura.model';

export interface IItemsAssinatura {
  id?: number;
  padariaId?: number;
  nome?: string;
  valor?: number;
  fotoContentType?: string | null;
  foto?: string | null;
  assinatura?: IAssinatura | null;
}

export const defaultValue: Readonly<IItemsAssinatura> = {};
