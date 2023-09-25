export interface News {
  id: number;
  title: string;
  date: string;
  content: string;
  mediaCompanyNum: number;
  link: string;
}
export interface StockPPP {
  stockId: number;
  name: string;
  code: number;
}

export interface Stock {
  name: string;
  code: string;
  // openPrice: string;
  market_sum: string;
  price_now: string;
  price_rate: string;
  amount: string;
  price_high: string;
  price_low: string;

  // newslist: News[];
}

export interface Theme {
  themeId: number;
  name: string;
}

// 워드클라우드 type
export interface KeywordProps {
  id: number;
  text: string;
  value: number;
}

export interface ThemeProps {
  keywords: Keyword[];
  stocks: Stock[];
}

export interface Keyword {
  id: number;
  name: string;
  themes: Theme[];
  newslist: News[];
}
