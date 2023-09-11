export interface Article {
    title: string;
    date?: string;
    content: string;
    company?: string;
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

export interface KeywordProps {
    id: string;
    name: string;
}

export interface Keyword {
    name: string;
    themes: string[];
    id: string;
    articles: Article[];
}
