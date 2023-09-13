export interface News {
  id: number;
  title: string;
  date: string;
  content: string;
  company: string;
}

// export interface Stock {
//     name: string;
//     code: number;
//     articles: Article[];
// }

export interface Theme {
  id: number;
  name: string;
}

// 워드클라우드 type
export interface KeywordProps {
  id: number;
  text: string;
  value: number;
}

export interface Keyword {
  id: number;
  name: string;
  themes: Theme[];
  newslist: News[];
}
