package kr.co.wikibook.gallery.item;

import kr.co.wikibook.gallery.common.util.MyfileUtils;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.item.model.ItemPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final MyfileUtils myfileUtils;

    public int save(MultipartFile img , ItemPostReq req) {
        String savedFileName = myfileUtils.makeRandomFileName(img); // 저장할 파일명
        req.setImgPath(savedFileName);
        int result = itemMapper.save(req);

        String directoryPath = String.format("/item/%d", req.getId());
        myfileUtils.makeFolders(directoryPath);

        String savedPathFileName = directoryPath + "/" + savedFileName;
        try{
            myfileUtils.transferTo(img,savedPathFileName );
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    public List<ItemGetRes> findAll(List<Integer> id) {
        return itemMapper.findAllByIdin(id);
    }

}
