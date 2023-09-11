// 랭킹 키워드 호출하기 :: 전체 일괄 호출(현재 순위 25개)
export const fetchKeywords = async () => {
    const res = await fetch("url");
    const data = await res.json();
    return data;
};

// 키워드 상세 조회하기 :: 키워드 아이디로 GET
export const fetchKeywordDetail = async () => {
    const res = await fetch("url");
    const data = await res.json();
    return data;
};

// 테마에 따른 종목 조회하기
export const fetchStockByTheme = async () => {
    const res = await fetch("url");
    const data = await res.json();
    return data;
};

// 종목 상세 조회하기 :: 종목 아이디로 GET
export const fetchStockDetail = async () => {
    const res = await fetch("url");
    const data = await res.json();
    return data;
};
