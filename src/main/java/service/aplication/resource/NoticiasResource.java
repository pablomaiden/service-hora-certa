/*
 * package service.aplication.resource;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import org.springframework.beans.BeanUtils; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import service.aplication.dto.PortalNoticiasDTO; import
 * service.aplication.model.Noticia; import
 * service.aplication.repository.NoticiasRepository; import
 * service.aplication.util.Util;
 * 
 * @RestController
 * 
 * @RequestMapping(value="/noticias") public class NoticiasResource extends
 * Abstract{
 * 
 * @Autowired private NoticiasRepository portalNoticiasRepository; //@Autowired
 * //private NoticiasService noticiasService;
 * 
 * @GetMapping public List<PortalNoticiasDTO> noticias() {
 * List<PortalNoticiasDTO> dtos = new ArrayList<PortalNoticiasDTO>();
 * PortalNoticiasDTO dto; try{ List<Noticia> noticias =
 * portalNoticiasRepository.listAllOrderDataNoticia(); int i=0; for(Noticia
 * temp: noticias){ dto = new PortalNoticiasDTO();
 * BeanUtils.copyProperties(temp,dto);
 * //dto.setDataNoticia(Util.formatterDate(temp.getDataNoticia())); if(i++>0)
 * dtos.add(dto); } return dtos; }catch (Exception e) {
 * System.out.print("Erro:"+e.getStackTrace()); } return null; }
 * 
 * @GetMapping(value= "/noticiaDestaque") public PortalNoticiasDTO
 * byNoticiaDestaque() { PortalNoticiasDTO dto; try{ List<Noticia> noticia =
 * portalNoticiasRepository.noticiaDestaque();
 * 
 * for(Noticia temp : noticia) { temp.setImagePath(
 * "https://s3.amazonaws.com/dashboard-sistemas-arquivos-geral/"+temp.
 * getImagePath()); }
 * 
 * dto = new PortalNoticiasDTO(); BeanUtils.copyProperties(noticia.get(0),dto);
 * //dto.setDataNoticia(Util.formatterDate(noticia.get(0).getDataNoticia()));
 * return dto; }catch (Exception e) {
 * System.out.print("Erro:"+e.getStackTrace()); } return null; }
 * 
 * @GetMapping(value= "/noticiaRelacionadas/{id}") public
 * List<PortalNoticiasDTO> noticiaRelacionadas(@PathVariable("id") Long id) {
 * List<PortalNoticiasDTO> dtos = new ArrayList<PortalNoticiasDTO>();
 * PortalNoticiasDTO dto; try{ List<Noticia> noticias =
 * portalNoticiasRepository.noticiaRelacionadasPorTag(id); for(Noticia temp:
 * noticias){ dto = new PortalNoticiasDTO(); BeanUtils.copyProperties(temp,dto);
 * dto.setDataNoticia(Util.formatterDate(temp.getDataNoticia())); dtos.add(dto);
 * }
 * 
 * for(PortalNoticiasDTO temp : dtos) { temp.setImagePath(
 * "https://s3.amazonaws.com/dashboard-sistemas-arquivos-geral/"+temp.
 * getImagePath()); }
 * 
 * }catch (Exception e) { System.out.print("Erro:"+e.getStackTrace()); } return
 * dtos; }
 * 
 * @GetMapping(value= "/getByIdNoticia/{id}") public
 * ResponseEntity<PortalNoticiasDTO> getByIdNoticia(@PathVariable("id") Long id)
 * { PortalNoticiasDTO dto = new PortalNoticiasDTO(); try{ Noticia noticia =
 * portalNoticiasRepository.getById(id); BeanUtils.copyProperties(noticia,dto);
 * dto.setImagePath(
 * "https://s3.amazonaws.com/dashboard-sistemas-arquivos-geral/"+dto.
 * getImagePath());
 * dto.setDataNoticia(Util.formatterDate(noticia.getDataNoticia())); return
 * (noticia==null? new
 * ResponseEntity<PortalNoticiasDTO>(dto,httpHeaders(),HttpStatus.NOT_FOUND) :
 * new ResponseEntity<PortalNoticiasDTO>(dto,httpHeaders(),HttpStatus.OK));
 * }catch (Exception e) { dto.setMensagemErro(e.getMessage()); return new
 * ResponseEntity<PortalNoticiasDTO>(dto,httpHeaders(),HttpStatus.BAD_REQUEST);
 * } }
 * 
 * 
 * }
 */