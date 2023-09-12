export const fetchKeywords = async () => {
    const res = await fetch("url");
    const data = await res.json();
    return data;
};
