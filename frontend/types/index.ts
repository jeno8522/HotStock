export interface Article {
    title: string;
    date: string;
    content: string;
    company: string;
}

export interface Stock {
    name: string;
    code: number;
    articles: Article[];
}

export interface Theme {
    name: string;
    id: number;
}

export interface Keyword {
    name: string;
    themes: string[];
    id: string;
    // articles: Article[];
}
