export interface News {
  id: number;
  title: string;
  date: string;
  content: string;
  company: number;
  url: string;
}
export interface StockProp {
  id: number;
  name: string;
}

export interface Stock {
  stockName: string;
  code: string;
  openPrice: number;
  currPrice: number;
  fluctuationRate: number;
  diff: number;
  tradingVolume: number;
  highPrice: number;
  lowPrice: number;

  newslist: News[];
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
