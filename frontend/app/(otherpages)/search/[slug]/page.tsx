const SearchResult = ({ params }: { params: { slug: string } }) => {
    return <div>검색 상세 페이지
        <div>{params.slug}의 검색 결과임</div>
    </div>;
};

export default SearchResult;
