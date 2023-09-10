import Link from "next/link";
import { KeywordNowFormat, KeywordRes } from "../../types/index";
import { GetServerSideProps } from "next";

interface getKeywordsProps {
    fetchedKeywords: KeywordNowFormat[];
}
const getKeywordsNow: GetServerSideProps<getKeywordsProps> = async () => {
    // try {
    const res = await fetch("url", { cache: "no-store" });
    const data: KeywordRes = await res.json();
    return {
        props: {
            fetchedKeywords: data.keywords,
        },
    };
    // } catch (err) {
    //     console.log(err);
    // }
};

const RankingList = async ({ fetchedKeywords }: getKeywordsProps) => {
    return (
        <div>
            <div>
                {/* {keywords.map((keyword, index) => (
                    <div key={index}>
                        <Link href={`/keyword/${keyword.id}`}>
                            {keyword.name}
                        </Link>
                    </div>
                ))} */}
            </div>
        </div>
    );
};
export default RankingList;
