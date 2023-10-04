import { Theme } from "@/types";
import { fetchThemes } from "@/utils";
import { ThemeList, ThemeBar } from "@/components";

const Themelist = async () => {
  const themesResult = await fetchThemes();

  const themeList = themesResult;

  // console.log(themeList);

  const themeIsEmpty =
    !Array.isArray(themeList) || themeList.length < 1 || !themeList;

  // ----------------------------------------
  return (
    <div className="m-10 lg:px-36 h-fit sm:px-10">
      <div className="text-[#24364d] font-bold">
        Hot Stock이 갖고있는 모든 테마들을 보여드려요
      </div>
      <div className="text-[#24364d] font-bold">
        궁금한 테마를 클릭해 테마에 해당되는 주식 종목들을 확인해보세요
      </div>
      <div>
        {!themeIsEmpty ? (
          <div>
            <ThemeList themeList={themeList} />

            {/* {themeList.map((theme) => (
              <ThemeBar
                key={theme.themeId}
                themeId={theme.themeId}
                name={theme.name}
              />
            ))} */}
          </div>
        ) : (
          <div>테마 혹은 이 테마에 해당하는 종목이 존재하지 않습니다.</div>
        )}
      </div>
    </div>
  );
};

export default Themelist;
